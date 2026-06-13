package com.abhishek.enterprise_inventory_system.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abhishek.enterprise_inventory_system.dto.LoginRequest;
import com.abhishek.enterprise_inventory_system.dto.RegisterRequest;
import com.abhishek.enterprise_inventory_system.entity.User;
import com.abhishek.enterprise_inventory_system.exception.ResourceNotFoundException;
import com.abhishek.enterprise_inventory_system.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

	@InjectMocks
	private AuthServiceImpl authService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PasswordEncoder passordEncoder;
	
	@Test
	public void shouldRegister() {
		RegisterRequest request = new RegisterRequest();
		request.setEmail("john@gmai.com");
		request.setPassword("john2131");
		request.setRole("CUSTOMER");
		request.setUsername("JOHN");
		
		User user = new User();
		
		when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		String result = authService.register(request);
		assertEquals("User Registered Successfully", result);
		
		when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
		assertThrows(ResourceNotFoundException.class, () -> authService.register(request));
	}
	
	@Test
	public void shouldLoginUser() {
		LoginRequest request = new LoginRequest();
		request.setEmail("aarav@gmail.com");
		request.setPassword("aarav121");
		
		User user = new User();
		user.setPassword("aarav121");
		
		when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
		assertThrows(ResourceNotFoundException.class, () -> authService.loginUser(request));
	}
	
	
}
