package com.language.model.exceptions;


@SuppressWarnings("serial")
public class UnexpectedTokenException extends ParsingException {

	public UnexpectedTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedTokenException(String message) {
		super(message);
	}

	public UnexpectedTokenException(Throwable cause) {
		super(cause);
	}

}