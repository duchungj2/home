package com.home.service;

import com.home.dto.RefreshTokenDTO;
import com.home.dto.response.AuthResponse;
import com.home.entity.RefreshTokenEntity;
import com.home.exception.RefreshTokenException;

public interface RefreshTokenService {
	
	AuthResponse refreshToken(String requestRefreshToken);
	
	RefreshTokenDTO createRefreshToken(Long userId);
	
	boolean verifyExpiration(RefreshTokenEntity refreshToken);
	
	int deleteByUserId(Long userId);
	
	RefreshTokenEntity findByToken(String token) throws RefreshTokenException;

}
