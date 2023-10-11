package com.home.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {
	
	@NotBlank(message = "Refresh token is required")
	private String refreshToken;
}
