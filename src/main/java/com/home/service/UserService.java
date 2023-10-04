package com.home.service;

import com.home.dto.request.CreateUserRequest;
import com.home.entity.UserEntity;

public interface UserService {
	
	UserEntity createUser(CreateUserRequest createUserRequest);
}
