package com.besuikerd.core.exception;

public class NetworkCraftException extends Exception{

	public NetworkCraftException() {
		super();
	}

	public NetworkCraftException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NetworkCraftException(String message, Throwable cause) {
		super(message, cause);
	}

	public NetworkCraftException(String message) {
		super(message);
	}

	public NetworkCraftException(Throwable cause) {
		super(cause);
	}
	
}
