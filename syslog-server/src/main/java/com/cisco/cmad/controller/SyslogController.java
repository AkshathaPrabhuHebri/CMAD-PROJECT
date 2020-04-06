package com.cisco.cmad.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.cmad.config.JwtTokenUtil;
import com.cisco.cmad.dao.SyslogRepository;
import com.cisco.cmad.dao.UserRepository;
import com.cisco.cmad.dto.SeverityStatistics;
import com.cisco.cmad.model.Syslog;
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
	
	//find all logs
	@RequestMapping(path = "/logs", method = RequestMethod.GET)
	public ResponseEntity<List<Syslog>> findAllLogs() {
		List<Syslog> logs = repo.findAll();
		return new ResponseEntity<List<Syslog>>(logs, HttpStatus.OK);
	}
	
	//get logs with start and end time
	@RequestMapping(path = "/log", method = RequestMethod.GET)
	public ResponseEntity<List<Syslog>> findByTimePeriod(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime) {
		List<Syslog> logs = repo.findByTimestampBetween(startTime,endTime);
		return new ResponseEntity<List<Syslog>>(logs, HttpStatus.OK);
	}
	
	//Returns an array of arrays with severity and corresponding count
	@RequestMapping(path = "/log/severity/count", method = RequestMethod.GET)
	public ResponseEntity<List<SeverityStatistics>> getStats(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime) {	
		//System.out.println(startTime);
		Date sDate = new Date(startTime.getTime());
		//System.out.println(sDate);
		Date eDate = new Date(endTime.getTime());
		//System.out.println(eDate);
		String username=jwtUserDetailsService.getUserNameFromAuthenticationContext();
		logger.info("Request received from user {} to get severity count",username);
		AggregationResults<org.bson.Document> sevStatlistTemp  =  repo.sumSeverityAndReturnAggregationResultWrapper(sDate,eDate);
		List<org.bson.Document> sevStatlist = sevStatlistTemp.getMappedResults();
		System.out.println(sevStatlist);
		List<SeverityStatistics> count = new ArrayList<SeverityStatistics>();
		
		for (org.bson.Document doc : sevStatlist) {
			count.add(new SeverityStatistics(Integer.parseInt(doc.get("_id").toString()), Long.parseLong(doc.get("total").toString()) ));
		}
		return new ResponseEntity<List<SeverityStatistics>>(count, HttpStatus.OK);
	}
	
//	//create a single user
//	@RequestMapping(path = "/user", method = RequestMethod.POST)
//	public ResponseEntity<User> addUser(@RequestBody User user) {
//		System.out.println(user);
//		userRepo.save(user);
//		return new ResponseEntity<User>(user, HttpStatus.CREATED);
//	}

	
}
