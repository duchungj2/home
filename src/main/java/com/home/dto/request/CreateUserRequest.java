package com.home.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
	
	@NotBlank(message = "Please enter email")
	private String email;
	@NotBlank(message = "Please enter password")
	private String password;
	private String phoneNumber;
	private String firstName;
	private String lastName;
}
