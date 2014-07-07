package com.besuikerd.core.exception;

public class NetworkCraftProtocolException extends NetworkCraftException{

	public NetworkCraftProtocolException() {
		super();
	}

	public NetworkCraftProtocolException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NetworkCraftProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	public NetworkCraftProtocolException(String message) {
		super(message);
	}

	public NetworkCraftProtocolException(Throwable cause) {
		super(cause);
	}
	
}
