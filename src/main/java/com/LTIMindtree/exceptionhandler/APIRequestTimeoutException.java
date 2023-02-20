package com.LTIMindtree.exceptionhandler;

public class APIRequestTimeoutException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public APIRequestTimeoutException(String message) {
        super(message);
    }
}
