package com.cisco.cmad.dto;

public class SeverityStatistics {
	
	private int severity;
	private long count;
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	public SeverityStatistics(int severity, long count) {
		super();
		this.severity = severity;
		this.count = count;
	}
	
	
	//private Severity sevName;
	
	
	
//	SevStat(Integer sevCode,long sevcnt){
//		this.sevCode = sevCode;
//		this.sevName = Severity.getSevName(sevCode);
//		this.sevcnt = sevcnt;
//	}
//	
//	public Integer getSevCode() {
//		return sevCode;
//	}
//	public void setSevCode(Integer sevCode) {
//		this.sevCode = sevCode;
//		this.sevName = Severity.getSevName(sevCode);
//	}
//	public long getSevcnt() {
//		return sevcnt;
//	}
//	public void setSevcnt(long sevcnt) {
//		this.sevcnt = sevcnt;
//	}
//	public Severity getSevName() {
//		return sevName;
//	}
//	public void setSevName(Severity sevName) {
//		this.sevName = sevName;
//	}
//	
		
}
