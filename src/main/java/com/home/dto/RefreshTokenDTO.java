package com.home.dto;

import java.sql.Timestamp;

import com.home.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDTO {
	private long id;

	private UserDTO user;

	private String token;

	private Timestamp expiryDate;
}
