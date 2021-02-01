package com.example.backend.controller;

import com.example.backend.data.ErrorInfo;
import com.example.backend.exception.http.HttpUnauthorizedException;
import com.example.backend.exception.io.MediaUploadException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.Properties;

@ControllerAdvice
@Component
public class ErrorController {
	@Resource(name = "errorMessages")
	private Properties properties;

	@ExceptionHandler({
			NoSuchElementException.class,
	})
	protected ResponseEntity<ErrorInfo> handleNotFound(RuntimeException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return getErrorInfoResponseEntity(ex, request, status);
	}

	@ExceptionHandler({
			IllegalArgumentException.class,
			SQLIntegrityConstraintViolationException.class,
			DataIntegrityViolationException.class,
			HttpMessageNotReadableException.class,
	})
	protected ResponseEntity<ErrorInfo> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return getErrorInfoResponseEntity(ex, request, status);
	}


	@ExceptionHandler({
			HttpUnauthorizedException.class,
			UsernameNotFoundException.class
	})
	protected ResponseEntity<ErrorInfo> handleUnauthorized(RuntimeException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return getErrorInfoResponseEntity(ex, request, status);
	}

	@ExceptionHandler({
			AccessDeniedException.class,
	})
	protected ResponseEntity<ErrorInfo> handleForbidden(RuntimeException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		return getErrorInfoResponseEntity(ex, request, status);
	}

	@ExceptionHandler({
			MediaUploadException.class,
			Exception.class
	})
	protected ResponseEntity<ErrorInfo> handleInternalServerError(RuntimeException ex, HttpServletRequest request) {
		ex.printStackTrace();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return getErrorInfoResponseEntity(ex, request, status);
	}

	private String getErrorMessage(String messageProp) {
		String message = properties.getProperty(messageProp);
		return message == null ? messageProp : message;
	}

	private ResponseEntity<ErrorInfo> getErrorInfoResponseEntity(RuntimeException exception, HttpServletRequest request, HttpStatus status) {
		ErrorInfo errorInfo = new ErrorInfo(status, request, getErrorMessage(exception));
		return ResponseEntity.status(errorInfo.getCode()).body(errorInfo);
	}

	private String getErrorMessage(Exception exception) {
		if (exception instanceof DataIntegrityViolationException) {
			return getErrorMessage(exception.getCause().getCause().getMessage());
		}

		if (exception instanceof HttpMessageNotReadableException) {
			return getErrorMessage(exception.getCause().getMessage());
		}

		return getErrorMessage(exception.getMessage());
	}
}
