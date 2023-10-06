package com.home.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.home.converter.UserConverter;
import com.home.dto.UserDTO;
import com.home.dto.request.CreateUserRequest;
import com.home.entity.UserEntity;
import com.home.repository.UserRepository;
import com.home.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
//	@Autowired
//	private UserConverter userConverter;
	
	@Autowired
    private UserRepository userRepository; 


	@Override
	public ResponseEntity<?> createUser(CreateUserRequest createUserRequest) {
//		UserEntity user = userConverter.convertToUserEntity(createUserRequest);
		return new ResponseEntity(null);
	}

	@Override
	public UserDTO loadUserByEmail(String email) {
		Optional<UserEntity> userEntity = userRepository.findByEmail(email); 
		  
        // Converting userDetail to UserDetails 
		return userEntity.map(UserDTO::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email)); 
	}

}
