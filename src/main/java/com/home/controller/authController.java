package com.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.dto.request.CreateUserRequest;
import com.home.dto.request.LoginRequest;
import com.home.service.UserService;
import com.home.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class authController {
	
//	@Autowired
//    private UserService userService;
	
//	@Autowired
//    private JwtTokenUtil jwtTokenUtil;
//	
//	@Autowired
//    private AuthenticationManager authenticationManager;
	
//	@PostMapping("/regiester")
//    public String addNewUser(@RequestBody CreateUserRequest createUserRequest) {
//        return userService.createUser(createUserRequest);
//    }
	
//	@PostMapping("/login")
//    public String authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtTokenUtil.generateToken(loginRequest.getEmail());
//        } else {
//            throw new UsernameNotFoundException("invalid user request!");
//        }
//    }
}
