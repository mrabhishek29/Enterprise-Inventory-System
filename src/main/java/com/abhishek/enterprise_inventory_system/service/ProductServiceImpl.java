package com.abhishek.enterprise_inventory_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.enterprise_inventory_system.dto.ProductRequestDTO;
import com.abhishek.enterprise_inventory_system.dto.ProductResponseDTO;
import com.abhishek.enterprise_inventory_system.entity.Product;
import com.abhishek.enterprise_inventory_system.exception.ResourceNotFoundException;
import com.abhishek.enterprise_inventory_system.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductResponseDTO createProduct(ProductRequestDTO request) {
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());
		Product savedProduct = productRepository.save(product);
		
		ProductResponseDTO response = new ProductResponseDTO();
		response.setId(savedProduct.getId());
		response.setName(savedProduct.getName());
		response.setDescription(savedProduct.getDescription());
		response.setPrice(savedProduct.getPrice());
		response.setStock(savedProduct.getStock());
		return response;
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		List<Product> products = productRepository. findAll();
			return products.stream()
				.map(this::mapToResponse)
				.toList();
	}

	@Override
	public ProductResponseDTO getProductById(Long Id) {
		Product product = productRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("product not found with id "+Id ));
		return mapToResponse(product);
	}

	@Override
	public ProductResponseDTO updateFullProduct(Long Id, ProductRequestDTO product) {
		Product UpdatedProduct = productRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		UpdatedProduct.setName(product.getName());
		UpdatedProduct.setDescription(product.getDescription());
		UpdatedProduct.setPrice(product.getPrice());
		UpdatedProduct.setStock(product.getStock());
		Product finalProduct = productRepository.save(UpdatedProduct);
		return mapToResponse(finalProduct);
	}

	
	
	@Override
	public void deleteProduct(Long Id) {
		productRepository.deleteById(Id);
	}

	private ProductResponseDTO mapToResponse(Product product)
	{
		ProductResponseDTO response = new ProductResponseDTO();
		response.setId(product.getId());
		response.setDescription(product.getDescription());
		response.setName(product.getName());
		response.setStock(product.getStock());
		response.setPrice(product.getPrice());
		return response;
	}

	@Override
	public ProductResponseDTO updateProduct(Long Id, ProductRequestDTO request) {
		Product product = productRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
		
		if(request.getName() != null){
			product.setName(request.getName());
		}
		if(request.getDescription() != null){
			product.setDescription(request.getDescription());
		}
		if(request.getPrice() != null){
			product.setPrice(request.getPrice());
		}
		if(request.getStock() != null) {
			product.setStock(request.getStock());
		}
		Product updatedProduct = productRepository.save(product);
		return mapToResponse(updatedProduct);
	}

}