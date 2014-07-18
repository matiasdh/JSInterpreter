package com.language.model.exceptions;

@SuppressWarnings("serial")
public class ReferenceErrorException extends RuntimeException {

	public ReferenceErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReferenceErrorException(String message) {
		super(message);
	}

	public ReferenceErrorException(Throwable cause) {
		super(cause);
	}

}
