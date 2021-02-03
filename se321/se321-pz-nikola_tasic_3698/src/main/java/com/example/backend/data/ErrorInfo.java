package com.example.backend.data;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ErrorInfo {
	private String message;
	private Integer code;
	private String url;
	private String error;

	public ErrorInfo(HttpStatus status, HttpServletRequest request, String error) {
		this.message = status.getReasonPhrase();
		this.code = status.value();
		this.url = request.getRequestURI();
		this.error = error;
	}
}
