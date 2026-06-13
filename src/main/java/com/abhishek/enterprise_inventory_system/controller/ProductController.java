package com.abhishek.enterprise_inventory_system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.enterprise_inventory_system.dto.ProductRequestDTO;
import com.abhishek.enterprise_inventory_system.dto.ProductResponseDTO;
import com.abhishek.enterprise_inventory_system.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Tag(name = "Product APIs")
public class ProductController {

	private ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@PostMapping("/new")
	@Operation(summary = "Create new product")
	public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO product)
	{
		return service.createProduct(product);
	}
	
	@GetMapping("/all")
	@Operation(summary = "Get all products")
	public List<ProductResponseDTO> getAllProduct()
	{
		return service.getAllProducts();
	}
	
	@GetMapping("/Id")
	@Operation(summary = "Get product by ID")
	public ProductResponseDTO getProductById(@RequestParam Long Id) {
		return service.getProductById(Id);
	}
	
	@PutMapping("/Id")
	@Operation(summary = "Update full product by ID")
	public ProductResponseDTO updateFullProduct(@RequestParam Long Id, @RequestBody ProductRequestDTO product) {
		return service.updateFullProduct(Id, product);
	}
	
	@PatchMapping("/Id")
	@Operation(summary = "Update product data by Id")
	public ProductResponseDTO updateProduct(@RequestParam Long Id, @RequestBody ProductRequestDTO product) {
		return service.updateProduct(Id, product);
	}
	
	@DeleteMapping("/Id")
	@Operation(summary = "Delete product by ID")
	public void deleteProduct(@RequestParam Long Id) {
		service.deleteProduct(Id);
	}
}
