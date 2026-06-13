package com.abhishek.enterprise_inventory_system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.enterprise_inventory_system.dto.LoginRequest;
import com.abhishek.enterprise_inventory_system.dto.RegisterRequest;
import com.abhishek.enterprise_inventory_system.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth API")
public class AuthController {

	private final AuthService authService;
	
	public AuthController(AuthService authService)
	{
		this.authService = authService;
	}
	
	@PostMapping("/register")
	@Operation(summary = "Register new user")
	public String registerUser(@RequestBody RegisterRequest registerUser)
	{
		return authService.register(registerUser);
	}
	
	@PostMapping("/login")
	@Operation(summary = "Login user")
	public String loginUser(@RequestBody LoginRequest request) {
		return authService.loginUser(request);
	}
}
