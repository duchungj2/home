package com.home.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.home.converter.RefreshTokenConverter;
import com.home.dto.RefreshTokenDTO;
import com.home.dto.response.AuthResponse;
import com.home.entity.RefreshTokenEntity;
import com.home.exception.RefreshTokenException;
import com.home.repository.RefreshTokenRepository;
import com.home.repository.UserRepository;
import com.home.service.RefreshTokenService;
import com.home.utils.JwtUtils;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private RefreshTokenConverter refreshTokenConverter;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtils jwtUtil;

	@Value("${jwt.refresh.expired}")
	private int refreshTokenExpired;
	
	@Override
	public AuthResponse refreshToken(String requestRefreshToken) {
		RefreshTokenEntity refreshTokenEntity = findByToken(requestRefreshToken);
		if (verifyExpiration(refreshTokenEntity) == false) {
			throw new RefreshTokenException("Refresh token was expired. Please make a new signin request");
		}
		
		String accessToken = jwtUtil.generateToken(refreshTokenEntity.getUser().getEmail());
		
		refreshTokenEntity.setToken(UUID.randomUUID().toString());
		refreshTokenEntity = refreshTokenRepository.save(refreshTokenEntity);
		
		
		return new AuthResponse(accessToken, refreshTokenEntity.getToken());
	}
	
	@Override
	public RefreshTokenEntity findByToken(String refreshToken) throws RefreshTokenException {
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken)
				.orElseThrow(() -> new RefreshTokenException("Refresh token does not exists"));
		return refreshTokenEntity;
	}
	
	
	

	@Override
	public RefreshTokenDTO createRefreshToken(Long userId) {
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByUserId(userId);
		
		if (refreshTokenEntity == null) {
			refreshTokenEntity = new RefreshTokenEntity();
		}

		refreshTokenEntity.setUser(userRepository.findById(userId).get());
		
		Instant instant = Instant.now();
		refreshTokenEntity.setExpiryDate(Timestamp.from(instant.plus(refreshTokenExpired, ChronoUnit.DAYS)));
		refreshTokenEntity.setToken(UUID.randomUUID().toString());
		refreshTokenEntity = refreshTokenRepository.save(refreshTokenEntity);
		return refreshTokenConverter.convertToRefreshTokenDTO(refreshTokenEntity);
	}

	@Override
	public boolean verifyExpiration(RefreshTokenEntity refreshTokenEntity) {
		return refreshTokenEntity.getExpiryDate().compareTo(new Timestamp(System.currentTimeMillis())) > 0;
	}

	@Override
	public int deleteByUserId(Long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
