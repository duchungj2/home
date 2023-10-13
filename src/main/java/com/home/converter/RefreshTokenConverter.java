package com.home.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.home.dto.RefreshTokenDTO;
import com.home.dto.UserDTO;
import com.home.entity.RefreshTokenEntity;

@Component
public class RefreshTokenConverter {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public RefreshTokenDTO convertToRefreshTokenDTO(RefreshTokenEntity refreshTokenEntity) throws ParseException {
		RefreshTokenDTO refreshTokenDTO = modelMapper.map(refreshTokenEntity, RefreshTokenDTO.class);
		refreshTokenDTO.setUser(new UserDTO(refreshTokenEntity.getUser()));
	    return refreshTokenDTO;
	}

}
