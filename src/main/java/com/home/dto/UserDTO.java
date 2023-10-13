package com.home.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.entity.UserEntity;

import lombok.Getter; 

@Getter
public class UserDTO implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String email;
	
	@JsonIgnore
	private String password;
	
	private boolean enabled;
	
	private String phoneNumber;
	
	private String firstName;
	
	private String lastName;
	
	private List<GrantedAuthority> authorities; 

	public UserDTO(UserEntity userEntity) { 
		id = userEntity.getId();
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

	@Override
	public String getUsername() {
		// Don't use username
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
} 

