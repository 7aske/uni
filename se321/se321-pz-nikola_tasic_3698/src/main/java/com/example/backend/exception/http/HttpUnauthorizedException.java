package com.example.backend.exception.http;

public class HttpUnauthorizedException extends RuntimeException {
	public HttpUnauthorizedException() {
	}

	public HttpUnauthorizedException(String s) {
		super(s);
	}

	public HttpUnauthorizedException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public HttpUnauthorizedException(Throwable throwable) {
		super(throwable);
	}
}
