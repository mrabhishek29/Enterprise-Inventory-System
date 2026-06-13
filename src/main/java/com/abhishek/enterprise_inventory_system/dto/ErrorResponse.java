package com.abhishek.enterprise_inventory_system.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	
	private int status;
	
	private String message;
	
	
	public ErrorResponse(LocalDateTime timestamp, int status, String message) {
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
