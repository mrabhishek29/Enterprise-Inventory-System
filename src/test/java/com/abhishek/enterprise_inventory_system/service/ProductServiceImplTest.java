package com.abhishek.enterprise_inventory_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abhishek.enterprise_inventory_system.dto.ProductRequestDTO;
import com.abhishek.enterprise_inventory_system.dto.ProductResponseDTO;
import com.abhishek.enterprise_inventory_system.entity.Product;
import com.abhishek.enterprise_inventory_system.exception.ResourceNotFoundException;
import com.abhishek.enterprise_inventory_system.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Test
	public void shouldCreateNewProduct(){
		ProductRequestDTO request = new ProductRequestDTO();
		request.setName("Television");
		request.setDescription("LG");
		request.setPrice(20000.0);
		request.setStock(10);
		
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setId(1L);
		product.setStock(request.getStock());
		product.setPrice(request.getPrice());
		
		when(productRepository.save(any(Product.class))).thenReturn(product);
		
		ProductResponseDTO response = productService.createProduct(request);
		assertEquals("LG", response.getDescription());
		assertEquals(10, response.getStock());
		verify(productRepository,times(1)).save(any(Product.class));
	}
	
	@Test
	public void returnAllProducts() {
		Product prod1 = new Product();
		prod1.setId(101L);
		prod1.setName("Laptop");
		prod1.setPrice(55000.0);
		prod1.setStock(40);
		prod1.setDescription("DELL");
		
		Product prod2 = new Product();
		prod2.setId(102L);
		prod2.setName("Fridge");
		prod2.setPrice(23000.0);
		prod2.setStock(28);
		prod2.setDescription("LG");
		when(productRepository.findAll()).thenReturn(List.of(prod1,prod2));
		
		List<ProductResponseDTO> response = productService.getAllProducts();
		assertEquals(55000.0, response.get(0).getPrice());
		assertEquals("Fridge", response.get(1).getName());
		assertEquals(2, response.size());
		verify(productRepository, times(1)).findAll();
	}
	
	@Test
	public void returnProductById() {
		Product product = new Product();
		product.setId(21L);
		product.setName("Mobile");
		product.setPrice(15000.0);
		product.setStock(90);
		product.setDescription("Apple");
		
		when(productRepository.findById(any(Long.TYPE))).thenReturn(Optional.of(product));
		ProductResponseDTO responseProd = productService.getProductById(21L);
		assertNotNull(responseProd);
		assertEquals("Mobile", responseProd.getName());
		  
		when(productRepository.findById(any(Long.TYPE))).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(34L));
		verify(productRepository, times(2)).findById(any(Long.TYPE));
	}
	
	@Test
	 public void shouldUpdateFullProduct() {
		ProductRequestDTO request = new ProductRequestDTO();
		request.setName("Mobile Accessories");
		request.setDescription("Charger");
		request.setPrice(1100.0);
		request.setStock(80);
		
		Product product1 = new Product();
		product1.setId(81L);
		product1.setName("Accessories");
		product1.setPrice(1500.0);
		product1.setStock(120);
		product1.setDescription("Earbuds");
		
		Product product2 = new Product();
		product2.setId(81L);
		product2.setName("Mobile Accessories");
		product2.setPrice(1100.0);
		product2.setStock(80);
		product2.setDescription("Charger");
		
		when(productRepository.findById(81L)).thenReturn(Optional.of(product1));
		when(productRepository.save(any(Product.class))).thenReturn(product2);
		
		ProductResponseDTO updatedProduct = productService.updateFullProduct(81L, request);
		
		assertEquals(1100.0, updatedProduct.getPrice());
		assertEquals(80, updatedProduct.getStock());
		assertEquals("Charger", updatedProduct.getDescription());

		verify(productRepository, times(1)).save(any(Product.class));
		
		when(productRepository.findById(any(Long.TYPE))).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(74L));
		verify(productRepository, times(2)).findById(any(Long.TYPE));
	 }
	
	@Test
	public void shouldDeleteRecord() {
		Long productId = 1L;
		productService.deleteProduct(productId);
		verify(productRepository).deleteById(productId);
	}
	
	@Test
	public void updatePartialProduct() {
		ProductRequestDTO request = new ProductRequestDTO();
		request.setName("Mobile");
		request.setDescription("Oppo");
		request.setPrice(25400.0);
		request.setStock(45);
		
		Product product = new Product();
		product.setName("Laptop");
		product.setDescription("HP");
		product.setPrice(45000.0);
		product.setStock(12);
		product.setId(1L);
		
		when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
		when(productRepository.save(any(Product.class))).thenReturn(product);
		
		ProductResponseDTO updatedProduct = productService.updateProduct(1L, request);
		
		assertEquals("Mobile", updatedProduct.getName());
		assertEquals("Oppo", updatedProduct.getDescription());
		assertEquals(25400.0, updatedProduct.getPrice());
		assertEquals(45, updatedProduct.getStock());
		
		verify(productRepository, times(1)).findById(any(Long.TYPE));
		verify(productRepository, times(1)).save(any(Product.class));
		
		when(productRepository.findById(any(Long.TYPE))).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(74L));
	}
	
}
