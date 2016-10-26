package org.spring.casapp;

public class SessionValidateException extends Exception {
	public SessionValidateException() {
		super("need login.");
	}

	public SessionValidateException(String message) {
		super(message);
	}

	public SessionValidateException(String message, Throwable cause) {
		super(message, cause);
	}
}
