package com.home.service;

import org.springframework.http.ResponseEntity;

import com.home.dto.UserDTO;
import com.home.dto.request.CreateUserRequest;
import com.home.entity.UserEntity;

public interface UserService {
	
	ResponseEntity<?> createUser(CreateUserRequest createUserRequest);
	
	UserDTO loadUserByEmail(String email);
}
