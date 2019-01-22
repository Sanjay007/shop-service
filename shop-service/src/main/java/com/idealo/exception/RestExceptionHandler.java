package com.idealo.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.idealo.output.APIResponse;
import com.idealo.utils.ErrorCode;

@ControllerAdvice
public class RestExceptionHandler {

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(JsonMappingException.class)
	public void handleInvalidTimestampException() {

	}
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public void handleInvalidMediaType() {

	}
	

	@ExceptionHandler
	public @ResponseBody APIResponse handle(MissingServletRequestParameterException exception) {
		exception.printStackTrace();
		return new APIResponse(ErrorCode.GIVEN_INPUT_PARAM_NOT_CORRECT, exception.getMessage());
	}

}
