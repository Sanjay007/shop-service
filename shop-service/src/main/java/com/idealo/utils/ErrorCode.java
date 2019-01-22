package com.idealo.utils;

public enum ErrorCode {

	INPUT_PARAM_NOT_CORRECT(10000, "Input parameters are not correct"), 
	GIVEN_INPUT_PARAM_NOT_CORRECT(10001,"Input parameters are not correct : {0}"),
	UNKNOWN_EXCEPTION(10002,"Something went wrong, please contact administrator with given id {0}"),
	CATEGORY_ALREADY_EXISTS(10003,"Category Already Present !"), 
	ITEM_ALREADY_EXISTS(10003,"Item Already Present !"),
	INVALID_CATEGORY(10003, "Invalid Category Id : {0}");

	private Integer errorCode;
	private String errorMessEn;

	private ErrorCode(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessEn = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessEn() {
		return errorMessEn;
	}

	public void setErrorMessEn(String errorMessEn) {
		this.errorMessEn = errorMessEn;
	}
}