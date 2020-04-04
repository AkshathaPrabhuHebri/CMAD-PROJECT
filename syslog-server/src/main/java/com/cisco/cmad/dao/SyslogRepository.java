package com.cisco.cmad.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import com.cisco.cmad.dto.SeverityStatistics;
import com.cisco.cmad.model.Syslog;

@Repository
public interface SyslogRepository extends JpaRepository<Syslog, String> {
	
	@Query(value="select * from syslog where timestamp between ?1 and ?2", nativeQuery = true)
	public List<Syslog> getSyslogInTimePeriod (String startTime, String endTime);
	
	@Query(value="select new com.cisco.cmad.dto.SeverityStatistics(severity, count(*)) from Syslog where timestamp between ?1 and ?2 group by severity")
	public  List<SeverityStatistics> syslogCountBySeverityInTimePeriod (Timestamp startTime, Timestamp endTime);
	
	public List<Syslog> findAllByTimestampBetween(
			@Temporal(TemporalType.TIMESTAMP) Date startTime,
			@Temporal(TemporalType.TIMESTAMP) Date endTime);

}
