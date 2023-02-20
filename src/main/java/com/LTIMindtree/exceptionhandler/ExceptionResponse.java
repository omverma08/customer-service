package com.LTIMindtree.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ExceptionResponse  {
	
	private Date timeStamp;
	private HttpStatus status;
	private String details;
	private String message;
	
	
	
	
	
	public ExceptionResponse(Date timeStamp, HttpStatus status, String details, String message) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.status = status;
		this.details = details;
		
	}
	ExceptionResponse(){
		
	}
	
	
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
