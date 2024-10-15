package com.semiProject.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoinRequest {
	private String userID;
	private String password;
	private String password2;
	private String username;
	private String email;
}