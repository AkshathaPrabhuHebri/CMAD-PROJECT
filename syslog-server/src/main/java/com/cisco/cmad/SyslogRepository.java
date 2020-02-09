package com.cisco.cmad;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SyslogRepository extends JpaRepository<Syslog, String> {
	
	@Query(value="select * from syslog where timestamp between ?1 and ?2", nativeQuery = true)
	public List<Syslog> getSyslogInTimePeriod (String startTime, String endTime);
	
	@Query(value="select severity, count(*) from syslog where timestamp between ?1 and ?2 group by severity", nativeQuery = true)
	public  List<SevStat> syslogCountBySeverityInTimePeriod (String startTime, String endTime);

}
