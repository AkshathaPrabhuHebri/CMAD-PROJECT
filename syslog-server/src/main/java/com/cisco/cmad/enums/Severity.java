package com.cisco.cmad.enums;

import java.util.HashMap;
import java.util.Map;

public enum Severity {
	Emergency(0), Alert(1), Critical(2), Error(3), Warning(4), Notice(5), Informational(6), Debug(7);

	private final int sevCode;
	
    public int getSevCode() 
    { 
        return this.sevCode; 
    } 

	private Severity(int sevCode) {
	//0-7
		this.sevCode = sevCode;
	}
	
	private static final Map<Integer, Severity> lookup = new HashMap<>();
	
    static
    {
        for(Severity sev : Severity.values())
        {
            lookup.put(sev.getSevCode(), sev);
        }
    }

    public static Severity getSevName(int sevCode)
    {
    	return lookup.get(sevCode);
    }
}
