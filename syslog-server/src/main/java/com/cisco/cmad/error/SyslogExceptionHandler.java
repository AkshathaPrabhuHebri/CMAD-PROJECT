package com.cisco.cmad.error;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cisco.cmad.exception.HttpErrorException;

@ControllerAdvice
public class SyslogExceptionHandler extends ResponseEntityExceptionHandler  {

	    // Let Spring BasicErrorController handle the exception, we just override the status code
	    @ExceptionHandler(HttpErrorException.class)
	    public void springHandleNotFound(HttpServletResponse response) throws IOException {
	        response.sendError(HttpStatus.BAD_REQUEST.value());
	    }

}
