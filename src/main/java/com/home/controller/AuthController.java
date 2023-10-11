package com.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.home.dto.request.CreateUserRequest;
import com.home.dto.request.LoginRequest;
import com.home.exception.UserAlreadyExistsException;
import com.home.service.UserService;
import com.home.utils.JwtUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
    private JwtUtils jwtUtil;
//	
	@Autowired
    private AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public void addNewUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
		createUserRequest.setEnabled(true);
		userService.createUser(createUserRequest);
	}

	@PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(loginRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request!");
        }
    }
}
