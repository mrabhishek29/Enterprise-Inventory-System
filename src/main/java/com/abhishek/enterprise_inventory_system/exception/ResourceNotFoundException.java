package com.abhishek.enterprise_inventory_system.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}