package com.abhishek.enterprise_inventory_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.enterprise_inventory_system.entity.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}