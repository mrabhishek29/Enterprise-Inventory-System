package com.abhishek.enterprise_inventory_system.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abhishek.enterprise_inventory_system.dto.LoginRequest;
import com.abhishek.enterprise_inventory_system.dto.RegisterRequest;
import com.abhishek.enterprise_inventory_system.entity.User;
import com.abhishek.enterprise_inventory_system.exception.ResourceNotFoundException;
import com.abhishek.enterprise_inventory_system.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
	{
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public String register(RegisterRequest request)
	{
		if(userRepository.findByEmail(request.getEmail())
				.isPresent()) {
			throw new ResourceNotFoundException("Email already exists");
		}
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		user.setRole(request.getRole());
		userRepository.save(user);
		return "User Registered Successfully";
	}

	@Override
	public String loginUser(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
		.orElseThrow(() -> new ResourceNotFoundException("Invalid Email"));
		
	    boolean isValidPassword = passwordEncoder.matches(request.getPassword(), user.getPassword());
		if(!isValidPassword)
			throw new ResourceNotFoundException("Invalid Password");
		return "Login Successfully";
	}

}
