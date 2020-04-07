package com.cisco.cmad.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cisco.cmad.model.Syslog;


@Repository
public interface SyslogRepository extends MongoRepository<Syslog, String> {
		
	@Aggregation(pipeline = {"{'$match': { '$and': [{'timestamp': {'$gt' : ?0}}, {'timestamp': {'$lt' : ?1}}, { 'deviceName': { '$in': ?2 } }]}}", "{ '$group' : { '_id' : '$severity', 'total' : { $sum: 1 } } }"})
	public AggregationResults<org.bson.Document> sumSeverityAndReturnAggregationResultWrapper(Date startTime, Date endTime, List<String> deviceList);
	
	@Aggregation("{'$match': { '$and': [{'timestamp': {'$gt' : ?0}}, {'timestamp': {'$lt' : ?1}}, { 'deviceName': { '$in': ?2 } }]}}")
	List<Syslog> findByTimestampBetween(Timestamp startTime, Timestamp endTime, List<String> deviceList);

}