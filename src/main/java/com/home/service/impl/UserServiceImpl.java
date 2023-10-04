package com.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.home.converter.UserConverter;
import com.home.dto.request.CreateUserRequest;
import com.home.entity.UserEntity;
import com.home.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserConverter userConverter; 

	@Override
	public UserEntity createUser(CreateUserRequest createUserRequest) {
		UserEntity user = userConverter.convertToUserEntity(createUserRequest);
		return null;
	}


}
