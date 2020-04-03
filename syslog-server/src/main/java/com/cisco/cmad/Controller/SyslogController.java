package com.cisco.cmad.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.cmad.DAO.SyslogRepository;
import com.cisco.cmad.DTO.SevStat;
import com.cisco.cmad.Model.Syslog;

@RestController
@CrossOrigin
public class SyslogController {

	@Autowired
	private SyslogRepository repo;

	//create a single log
	@RequestMapping(path = "/log", method = RequestMethod.POST)
	public ResponseEntity<Syslog> add(@RequestBody Syslog log) {
		repo.save(log);
		return new ResponseEntity<Syslog>(log, HttpStatus.CREATED);
	}
	
	
	//get logs with start and end time
	@RequestMapping(path = "/log", method = RequestMethod.GET)
	public ResponseEntity<List<Syslog>> findByTimePeriod(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime) {
		List<Syslog> logs = repo.findAllByTimestampBetween(startTime,endTime);
		return new ResponseEntity<List<Syslog>>(logs, HttpStatus.OK);
	}
	
	
	//Returns an array of arrays with severity and corresponding count
	@RequestMapping(path = "/log/severity/count", method = RequestMethod.GET)
	public ResponseEntity<List<SevStat>> getStats(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime) {
		List<SevStat> count = repo.syslogCountBySeverityInTimePeriod(startTime, endTime);
		return new ResponseEntity<List<SevStat>>(count, HttpStatus.OK);
	}


	
	
}
