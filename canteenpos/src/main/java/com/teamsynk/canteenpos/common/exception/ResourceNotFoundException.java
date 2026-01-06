package com.teamsynk.canteenpos.common.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String resource, Object id) {
        super(resource + " not found with id: " + id);
    }
	
	public ResourceNotFoundException(String resource, String message) {
        super(resource + " not found: " + message);
    }

}
