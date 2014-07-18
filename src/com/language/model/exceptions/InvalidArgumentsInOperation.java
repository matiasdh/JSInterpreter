package com.language.model.exceptions;

@SuppressWarnings("serial")
public class InvalidArgumentsInOperation extends RuntimeException {

	public InvalidArgumentsInOperation(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidArgumentsInOperation(String message) {
		super(message);
	}

	public InvalidArgumentsInOperation(Throwable cause) {
		super(cause);
	}

}
