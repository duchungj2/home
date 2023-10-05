package com.home.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.entity.UserEntity;

import lombok.Getter; 

@Getter
public class UserDTO {
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private boolean enabled;
	
	private String phoneNumber;
	
	private String firstName;
	
	private String lastName;
	
	private List<GrantedAuthority> authorities; 

	public UserDTO(UserEntity userEntity) { 
		email = userEntity.getEmail(); 
		password = userEntity.getPassword();
		enabled = userEntity.isEnabled();
		phoneNumber = userEntity.getPhoneNumber();
		firstName = userEntity.getFirstName();
		lastName = userEntity.getLastName();
		authorities = userEntity.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())) 
				.collect(Collectors.toList()); 
	}
} 

