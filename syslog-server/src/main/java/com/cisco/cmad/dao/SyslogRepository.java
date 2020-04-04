package com.cisco.cmad.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.Aggregation;

//import javax.persistence.TemporalType;

//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cisco.cmad.dto.SeverityStatistics;
import com.cisco.cmad.model.Syslog;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface SyslogRepository extends MongoRepository<Syslog, String> {
		
//	@Query(value="select new com.cisco.cmad.dto.SeverityStatistics(severity, count(*)) from Syslog where timestamp between ?1 and ?2 group by severity")
//	public  List<SeverityStatistics> syslogCountBySeverityInTimePeriod (Timestamp startTime, Timestamp endTime);
//	
//	public List<Syslog> findAllByTimestampBetween(
//			@Temporal(TemporalType.TIMESTAMP) Date startTime,
//			@Temporal(TemporalType.TIMESTAMP) Date endTime);
	//@Query(value="db.syslog.aggregate[{$match: {$and: [{timestamp: {$gt : ?0}}, {timestamp: {$lt : ?1}}]}},{$group: {_id : \"$severity\", number:  { $sum : 1} }}]")
	@Aggregation(pipeline = {"[{$match: {$and: [{timestamp: {$gt : ?0}}, {timestamp: {$lt : ?1}}]}},{$group: {_id : \\\"$severity\\\", number:  { $sum : 1} }}]"})
	public  List<Map<String,Object>> syslogCountBySeverityInTimePeriod(Timestamp startTime, Timestamp endTime);
	
//	public  List<SeverityStatistics> syslogCountBySeverityInTimePeriod(Timestamp startTime, Timestamp endTime);

	
	List<Syslog> findByTimestampBetween(Timestamp startTime, Timestamp endTime);

}
