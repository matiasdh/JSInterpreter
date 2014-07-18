package com.language.model.exceptions;

import com.language.model.expression.Statement;

@SuppressWarnings("serial")
public class UnexpectedStatement extends RuntimeException {
	
		public UnexpectedStatement(String message, Throwable cause) {
			super(message, cause);
		}

		public UnexpectedStatement(String message) {
			super("SyntaxError: Illegal " + message + " statement");
		}
		
		public UnexpectedStatement(Throwable cause) {
			super(cause);
		}
}
