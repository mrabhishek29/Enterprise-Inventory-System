package com.abhishek.enterprise_inventory_system.service;

import java.util.List;

import com.abhishek.enterprise_inventory_system.dto.ProductRequestDTO;
import com.abhishek.enterprise_inventory_system.dto.ProductResponseDTO;

public interface ProductService {
 
	ProductResponseDTO createProduct(ProductRequestDTO product);
	List<ProductResponseDTO> getAllProducts();
	ProductResponseDTO getProductById(Long id);
	ProductResponseDTO updateFullProduct(Long id, ProductRequestDTO product);
	ProductResponseDTO updateProduct(Long id, ProductRequestDTO product);
	void deleteProduct(Long id);
}