package com.cisco.cmad;

public enum Severity {
	
	Emergency(0), Alert(1), Critical(2), Error(3), Warning(4), Notice(5), Informational(6), Debug(7);
	
	private final int sevCode;
	
	private Severity(int sevCode) {
	//0-7
		this.sevCode = sevCode;
	}
}
