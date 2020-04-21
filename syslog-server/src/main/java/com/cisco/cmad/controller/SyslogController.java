package com.cisco.cmad.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.cmad.dao.SyslogRepository;
import com.cisco.cmad.dao.UserRepository;
import com.cisco.cmad.dto.SeverityStatistics;
import com.cisco.cmad.exception.HttpErrorException;
import com.cisco.cmad.model.Syslog;
import com.cisco.cmad.model.User;
import com.cisco.cmad.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class SyslogController {
	
	public static Logger logger = LoggerFactory.getLogger(SyslogController.class);

	@Autowired
	private SyslogRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	//create a single log
	@RequestMapping(path = "/log", method = RequestMethod.POST)
	public ResponseEntity<Syslog> add(@RequestBody Syslog log) {
		repo.save(log);
		return new ResponseEntity<Syslog>(log, HttpStatus.CREATED);
	}
	
//	//find all logs
//	@RequestMapping(path = "/log", method = RequestMethod.GET)
//	public ResponseEntity<List<Syslog>> findAllLogs() {
//		List<Syslog> logs = repo.findAll();
//		return new ResponseEntity<List<Syslog>>(logs, HttpStatus.OK);
//	}
	
	//get logs with start and end time
	@RequestMapping(path = "/log", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<Syslog>> findByTimePeriod(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime) {
		areTimeStampsValid(startTime, endTime);
		List<String> deviceList=getDevicesForUser();
		List<Syslog> logs = repo.findByTimestampBetween(startTime,endTime,deviceList);
		return new ResponseEntity<List<Syslog>>(logs, HttpStatus.OK);
	}
	
	//Returns an array of arrays with severity and corresponding count
	@RequestMapping(path = "/log/severity/count", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<SeverityStatistics>> getStats(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime) {			
		areTimeStampsValid(startTime, endTime);
		//System.out.println(startTime);
		Date sDate = new Date(startTime.getTime());
		//System.out.println(sDate);
		Date eDate = new Date(endTime.getTime());
		//System.out.println(eDate);
		List<String> deviceList=getDevicesForUser();
		logger.debug("Device list are {}",deviceList);
		AggregationResults<org.bson.Document> sevStatlistTemp  =  repo.sumSeverityAndReturnAggregationResultWrapper(sDate,eDate,deviceList);
		List<org.bson.Document> sevStatlist = sevStatlistTemp.getMappedResults();
		System.out.println(sevStatlist);
		List<SeverityStatistics> count = new ArrayList<SeverityStatistics>();
		
		for (org.bson.Document doc : sevStatlist) {
			count.add(new SeverityStatistics(Integer.parseInt(doc.get("_id").toString()), Long.parseLong(doc.get("total").toString()) ));
		}
		return new ResponseEntity<List<SeverityStatistics>>(count, HttpStatus.OK);
	}
	
	private List<String> getDevicesForUser() {
		String username=jwtUserDetailsService.getUserNameFromAuthenticationContext();
		logger.info("Request received from user {} to get severity count",username);
		User user=userRepo.findByUsername(username);
		return user.getDevices();
	}
	
	public static void areTimeStampsValid(Timestamp startTime, Timestamp endTime )
	{ 
		Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
		int b2 = startTime.compareTo(timestampNow);
		int b3 = endTime.compareTo(timestampNow);
		if(b2>0) {
			throw new HttpErrorException(String.format("Timestamp passed: %s is in the future", startTime), 11);
		}
		if (b3>0) {
			throw new HttpErrorException(String.format("Timestamp passed: %s is in the future", endTime), 11);
		}
	}
	
//	//create a single user
//	@RequestMapping(path = "/user", method = RequestMethod.POST)
//	public ResponseEntity<User> addUser(@RequestBody User user) {
//		System.out.println(user);
//		userRepo.save(user);
//		return new ResponseEntity<User>(user, HttpStatus.CREATED);
//	}

	
}
