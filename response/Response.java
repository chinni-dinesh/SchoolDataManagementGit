package com.schoolDataManagement.response;

public class Response<T> {

	private String status;
	private String message;
	private T data;

	public String isStatus() {
		return status;
	}

	public void setStatus(String success) {
		this.status = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
