package com.idealo.output;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.idealo.utils.ErrorCode;
import com.idealo.utils.MessageFormatter;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {

	private String errorId;
	private Integer errorCode;
	private String errorMsgEn;
	private boolean success = true;
	private Object data;
		
	public APIResponse() {}
	
	public APIResponse(Object data) {
		this.data = data;
	}
	
	public APIResponse(String errorId, Integer errorCode, String errorMsgAr, String errorMsgEn) {
		super();
		this.errorId = errorId;
		this.errorMsgEn = errorMsgEn;
		if(errorCode!=null){
			this.success = false;
		}
	}

	public APIResponse(Enum<?> code) {
		this(code, new String[] {});
	}

	public APIResponse(Enum<?> code, String param) {
		this(code, new String[] { param });
	}
	
	public APIResponse(Enum<?> code, String... params) {
		if (code instanceof ErrorCode) {
			fillErrorFields((ErrorCode) code, params);
		}
	}
	
	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsgEn() {
		return errorMsgEn;
	}
	public void setErrorMsgEn(String errorMsgEn) {
		this.errorMsgEn = errorMsgEn;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	private void fillErrorFields(ErrorCode errorCode, String... params) {
		setSuccess(false);
		this.errorId= errorCode.name();
		this.errorCode = errorCode.getErrorCode();
		MessageFormatter messageObj = new MessageFormatter();
		this.errorMsgEn = messageObj.getMessage(errorCode.getErrorMessEn(), params);
	}
}

