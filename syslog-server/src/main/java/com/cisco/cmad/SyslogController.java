package com.cisco.cmad;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<Syslog>> findByTimePeriod(@RequestParam(name = "startTime") String startTime, @RequestParam(name = "endTime") String endTime) {
		List<Syslog> logs = repo.getSyslogInTimePeriod(startTime,endTime);
		return new ResponseEntity<List<Syslog>>(logs, HttpStatus.OK);
	}
	
	
	//Returns an array of arrays with severity and corresponding count
	@RequestMapping(path = "/log/severity/count", method = RequestMethod.GET)
	public ResponseEntity<List<SevStat>> getStats(@RequestParam(name = "startTime") String startTime, @RequestParam(name = "endTime") String endTime) {
		List<SevStat> count = (List<SevStat>) repo.syslogCountBySeverityInTimePeriod(startTime, endTime);
		return new ResponseEntity<List<SevStat>>(count, HttpStatus.OK);
	}


	
	
}
