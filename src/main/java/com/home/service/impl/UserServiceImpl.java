package com.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.converter.UserConverter;
import com.home.dto.request.CreateUserRequest;
import com.home.entity.UserEntity;
import com.home.exception.UserAlreadyExistsException;
import com.home.repository.UserRepository;
import com.home.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	public void createUser(CreateUserRequest createUserRequest) {

		if (userRepository.existsByEmail(createUserRequest.getEmail())) {
			throw new UserAlreadyExistsException("User already exists!");
		}

		UserEntity user = userConverter.convertToUserEntity(createUserRequest);
		userRepository.save(user);
	}

}
