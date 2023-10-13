package com.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

import com.home.dto.RefreshTokenDTO;
import com.home.dto.UserDTO;
import com.home.dto.request.CreateUserRequest;
import com.home.dto.request.LoginRequest;
import com.home.dto.request.RefreshTokenRequest;
import com.home.dto.response.AuthResponse;
import com.home.exception.UserAlreadyExistsException;
import com.home.service.RefreshTokenService;
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

	@Autowired
	RefreshTokenService refreshTokenService;

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public void addNewUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
		createUserRequest.setEnabled(true);
		userService.createUser(createUserRequest);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, loginRequest.getPassword()));
		String accessToken = jwtUtil.generateToken(email);

		UserDTO userDTO = (UserDTO) authentication.getPrincipal();
		RefreshTokenDTO refreshToken = refreshTokenService.createRefreshToken(userDTO.getId());

		return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken.getToken()));
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<AuthResponse> refreshtoken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		String requestRefreshToken = refreshTokenRequest.getRefreshToken();
		
		AuthResponse authResponse = refreshTokenService.refreshToken(requestRefreshToken);
		
		return ResponseEntity.ok(authResponse);
	}
}
