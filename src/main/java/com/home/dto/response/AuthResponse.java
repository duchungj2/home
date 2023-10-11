package com.home.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponse {
	private final String accessToken;
	private String type = "Bearer";
	private final String refreshToken;
}
