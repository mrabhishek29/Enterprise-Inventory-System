package com.abhishek.enterprise_inventory_system.service;

import com.abhishek.enterprise_inventory_system.dto.LoginRequest;
import com.abhishek.enterprise_inventory_system.dto.RegisterRequest;

public interface AuthService {
	
  String register(RegisterRequest request);
  String loginUser(LoginRequest request);
}
