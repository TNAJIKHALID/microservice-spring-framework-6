package com.example.accountservice.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
@Component
public class ExceptionHandlers {

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseError handleThrowable(final Throwable ex) {
		log.error("An unexpected internal server error occured", ex);
		return new ResponseError("INTERNAL.SERVER.ERROR", "An unexpected internal server error occured" );
	}

	@AllArgsConstructor()
	public static class ResponseError {
		@Getter
		private final String code;
		@Getter
		private final String message;

	}
}