package com.cisco.cmad.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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
import com.cisco.cmad.model.User;

import org.springframework.data.mongodb.repository.Query;

@Repository
public interface SyslogRepository extends MongoRepository<Syslog, String> {
		
	@Aggregation(pipeline = {"{'$match': { '$and': [{'timestamp': {'$gt' : ?0}}, {'timestamp': {'$lt' : ?1}}]}}", "{ '$group' : { '_id' : '$severity', 'total' : { $sum: 1 } } }"})
	public AggregationResults<org.bson.Document> sumSeverityAndReturnAggregationResultWrapper(Date startTime, Date endTime);
		
	List<Syslog> findByTimestampBetween(Timestamp startTime, Timestamp endTime);

}