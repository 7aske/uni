package com.example.backend.exception.http;

public class HttpForbiddenException extends RuntimeException {
	public HttpForbiddenException() {
	}

	public HttpForbiddenException(String s) {
		super(s);
	}

	public HttpForbiddenException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public HttpForbiddenException(Throwable throwable) {
		super(throwable);
	}
}
