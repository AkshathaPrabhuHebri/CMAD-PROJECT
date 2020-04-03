package com.cisco.cmad.Model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Syslog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;
	@Column(name = "timestamp", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	private int severity;
	private String facility;
	private String message;
	private String deviceName;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
