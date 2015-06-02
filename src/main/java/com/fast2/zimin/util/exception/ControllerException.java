package com.fast2.zimin.util.exception;

@SuppressWarnings("serial")
public class ControllerException extends Exception {
	
	private String resultCode;
	private String msg;

	public ControllerException(String resultCode, String msg) {
		super();
		this.resultCode = resultCode;
		this.msg = msg;
	}

	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
