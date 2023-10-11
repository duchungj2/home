package com.home.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.home.converter.RefreshTokenConverter;
import com.home.dto.RefreshTokenDTO;
import com.home.entity.RefreshTokenEntity;
import com.home.exception.RefreshTokenNotFoundException;
import com.home.repository.RefreshTokenRepository;
import com.home.service.RefreshTokenService;

public class RefreshTokenServiceImpl implements RefreshTokenService {
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private RefreshTokenConverter refreshTokenConverter;

	@Override
	public RefreshTokenDTO createRefreshToken(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RefreshTokenDTO verifyExpiration(RefreshTokenEntity token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByUserId(Long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RefreshTokenDTO findByToken(String token) throws RefreshTokenNotFoundException {
		RefreshTokenEntity RefreshTokenEntity = refreshTokenRepository.findByToken(token).orElseThrow(() -> new RefreshTokenNotFoundException("Token does not exists"));
		return refreshTokenConverter.convertToRefreshTokenDTO(RefreshTokenEntity);
	}

}
