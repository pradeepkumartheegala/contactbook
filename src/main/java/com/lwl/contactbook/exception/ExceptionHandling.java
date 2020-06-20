package com.lwl.contactbook.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
	private Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

	@ExceptionHandler(value = Exception.class)
	public void handleException(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURI() + " has " + exception);
	}

}
