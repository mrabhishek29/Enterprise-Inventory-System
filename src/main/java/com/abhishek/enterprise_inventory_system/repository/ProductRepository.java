package com.abhishek.enterprise_inventory_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.enterprise_inventory_system.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
