package com.example.backend.exception.io;

public class MediaUploadException extends RuntimeException {
	public MediaUploadException() {
	}

	public MediaUploadException(String s) {
		super(s);
	}

	public MediaUploadException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public MediaUploadException(Throwable throwable) {
		super(throwable);
	}

	public MediaUploadException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
