package com.home.service;

import com.home.dto.UserDTO;
import com.home.dto.request.CreateUserRequest;

public interface UserService {
	
	void createUser(CreateUserRequest createUserRequest);
	
	UserDTO loadUserByEmail(String email);
}
