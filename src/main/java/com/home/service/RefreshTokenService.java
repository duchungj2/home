package com.home.service;

import com.home.dto.RefreshTokenDTO;
import com.home.entity.RefreshTokenEntity;
import com.home.exception.RefreshTokenNotFoundException;

public interface RefreshTokenService {
	
	RefreshTokenDTO createRefreshToken(Long userId);
	
	RefreshTokenDTO verifyExpiration(RefreshTokenEntity refreshToken);
	
	int deleteByUserId(Long userId);
	
	RefreshTokenDTO findByToken(String token) throws RefreshTokenNotFoundException;

}
