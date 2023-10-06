package com.home.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.home.dto.request.CreateUserRequest;
import com.home.entity.UserEntity;

@Component
public class UserConverter {
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
    private PasswordEncoder encoder;
	
	public UserEntity convertToUserEntity(CreateUserRequest createUserResquest) throws ParseException {
		UserEntity userEntity = modelMapper.map(createUserResquest, UserEntity.class);
		userEntity.setPassword(encoder.encode(createUserResquest.getPassword()));
	    return userEntity;
	}
}
