package com.cisco.cmad.App;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.cisco.cmad.*"})
@ComponentScan({"com.*"})
public class CiscoSyslogServer {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(CiscoSyslogServer.class, args);
	}
}
