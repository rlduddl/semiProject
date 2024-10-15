package com.semiProject.payload.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//JSON으로 리턴할 때 키의 값이 null이면 미포함시키는 어노테이션
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
	private String data;
	private boolean success;
	private String timestamp;
	private String cause;
	private String path;
	
	public ApiResponse(boolean success, String data) {
		this.timestamp = Instant.now().toString();
		this.success = success;
		this.data = data;
		this.cause = null;
		this.path = null;
	}
	
	public ApiResponse(boolean success, String data, String cause, String path) {
		this.timestamp = Instant.now().toString();
		this.success = success;
		this.data = data;
		this.cause = cause;
		this.path = path;
	}

}
