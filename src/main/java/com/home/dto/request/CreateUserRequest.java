package com.home.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
	
	@NotBlank(message = "Please enter email")
	@Email(message = "Email is not in correct format")
	private String email;
	
	@NotBlank(message = "Please enter password")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	@Size(max = 25, message = "password contains maximum 25 characters")
	private String password;
	
	@Pattern(regexp = "^((84|0)[3|5|7|8|9])+([0-9]{8})\\b", message = "Phone number is not in correct format")
	private String phoneNumber;
	
	@Size(min = 1, max = 50, message = "First name must be of 1 to 50 characters")
	@Pattern(regexp = "^[a-zA-Z]*$",
            message = "First name cannot contain special characters")
	private String firstName;
	
	@Size(min = 1, max = 50, message = "Last name must be of 1 to 50 characters")
	@Pattern(regexp = "^[a-zA-Z]*$",
            message = "Last name cannot contain special characters")
	private String lastName;
}
