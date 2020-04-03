package com.cisco.cmad.DTO;

public class SevStat {
	
	private Integer severity;
	private long count;
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public SevStat(Integer severity, long count) {
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
