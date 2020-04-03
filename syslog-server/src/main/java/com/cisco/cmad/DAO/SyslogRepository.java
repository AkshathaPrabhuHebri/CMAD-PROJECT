package com.cisco.cmad.DAO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import com.cisco.cmad.DTO.SevStat;
import com.cisco.cmad.Model.Syslog;

@Repository
public interface SyslogRepository extends JpaRepository<Syslog, String> {
	
	@Query(value="select * from syslog where timestamp between ?1 and ?2", nativeQuery = true)
	public List<Syslog> getSyslogInTimePeriod (String startTime, String endTime);
	
	@Query(value="select new com.cisco.cmad.SevStat(severity, count(*)) from Syslog where timestamp between ?1 and ?2 group by severity")
	public  List<SevStat> syslogCountBySeverityInTimePeriod (Timestamp startTime, Timestamp endTime);
	
	public List<Syslog> findAllByTimestampBetween(
			@Temporal(TemporalType.TIMESTAMP) Date startTime,
			@Temporal(TemporalType.TIMESTAMP) Date endTime);

}
