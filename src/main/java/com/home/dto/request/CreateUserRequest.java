package com.home.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
	private String email;
	private String password;
	private String phoneNumber;
	private String firstName;
	private String lastName;
}
