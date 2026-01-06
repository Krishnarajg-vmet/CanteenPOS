package com.teamsynk.canteenpos.common.exception;

import java.time.Instant;

public class ErrorResponse {

	private String message;
    private String details;
    private Instant timestamp;

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
        this.timestamp = Instant.now();
    }

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public Instant getTimestamp() {
		return timestamp;
	}


}
