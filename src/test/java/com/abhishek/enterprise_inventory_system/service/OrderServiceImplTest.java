package com.abhishek.enterprise_inventory_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abhishek.enterprise_inventory_system.dto.OrderRequestDTO;
import com.abhishek.enterprise_inventory_system.dto.OrderResponseDTO;
import com.abhishek.enterprise_inventory_system.entity.Order;
import com.abhishek.enterprise_inventory_system.entity.OrderStatus;
import com.abhishek.enterprise_inventory_system.entity.Product;
import com.abhishek.enterprise_inventory_system.entity.User;
import com.abhishek.enterprise_inventory_system.exception.ResourceNotFoundException;
import com.abhishek.enterprise_inventory_system.repository.OrderRepository;
import com.abhishek.enterprise_inventory_system.repository.ProductRepository;
import com.abhishek.enterprise_inventory_system.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private OrderServiceImpl service;
	
	@Test
	public void shouldCreateOrder() {
		Product product = new Product();
		product.setName("Mobiles");
		product.setId(1L);
		product.setStock(30);
		product.setPrice(100.0);
		
		User user = new User();
		user.setId(2L);
		
		Order order = new Order();
		order.setId(1L);
		order.setQuantity(10);
		order.setTotalPrice(BigDecimal.valueOf(23));
		order.setUser(user);
		order.setProduct(product);
		
		OrderRequestDTO request = new OrderRequestDTO();
		request.setProductId(1L);
		request.setUserId(2L);
		request.setQuantity(5);
		
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(userRepository.findById(2L)).thenReturn(Optional.of(user));
		
		when(productRepository.save(any(Product.class))).thenReturn(product);
		when(orderRepository.save(any(Order.class))).thenReturn(order);
		
		OrderResponseDTO response = service.createOrder(request);
		assertEquals("Mobiles", response.getProductName());
		assertEquals(10, response.getQuantity());
	}
	
	@Test
	public void getAllOrdersTest() {
		Product product = new Product();
		product.setName("Mobiles");
		
		User user = new User();
		user.setId(10L);
		
		Order order1 = new Order();
		order1.setId(10L);
		order1.setQuantity(21);
		order1.setTotalPrice(BigDecimal.valueOf(200));
		order1.setStatus(OrderStatus.CONFIRMED);
		order1.setProduct(product);
		order1.setUser(user);
		
		Order order2 = new Order();
		order2.setId(440L);
		order2.setQuantity(54);
		order2.setTotalPrice(BigDecimal.valueOf(333));
		order2.setProduct(product);
		order2.setUser(user);
		
		when(orderRepository.findAll()).thenReturn(List.of(order1, order2));
		List<OrderResponseDTO> response = service.getAllOrders();
		assertEquals(21, response.get(0).getQuantity());
		assertEquals(BigDecimal.valueOf(333), response.get(1).getTotalPrice());
		assertEquals("Mobiles", response.get(0).getProductName());
		verify(orderRepository,times(1)).findAll();
	}
	
	@Test
	public void shouldGetOrderbyID() {
		Product product = new Product();
		product.setName("Mobiles");
		
		User user = new User();
		user.setId(10L);
		
		Order order = new Order();
		order.setId(10L);
		order.setQuantity(21);
		order.setTotalPrice(BigDecimal.valueOf(200));
		order.setStatus(OrderStatus.CONFIRMED);
		order.setProduct(product);
		order.setUser(user);
		when(orderRepository.findById(10L)).thenReturn(Optional.of(order));
		OrderResponseDTO response = service.getOrderByID(order.getId());
		assertEquals(21, response.getQuantity());
		
		when(orderRepository.findById(12L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> service.getOrderByID(12L));
	}
	
	@Test
	public void shouldCancelOrder() {
		Long id = 1L;
		Product product = new Product();
		product.setStock(123);
		
		Order order = new Order();
		order.setQuantity(21);
		order.setId(id);
		order.setProduct(product);
		
		when(productRepository.save(any(Product.class))).thenReturn(product);
		when(orderRepository.findById(id)).thenReturn(Optional.of(order));
		service.cancelOrder(id);
		verify(orderRepository).save(order);
		verify(productRepository).save(product);
	}
	  
	@Test
	public void shouldUpdateOrderStatus() {
		Long id = 1L;
		Product product = new Product();
		product.setStock(123);
		
		User user = new User();
		user.setId(10L);
		
		Order order = new Order();
		order.setId(130L);
		order.setQuantity(212);
		order.setTotalPrice(BigDecimal.valueOf(250));
		order.setStatus(OrderStatus.SHIPPED);
		order.setProduct(product);
		order.setUser(user);
		
		when(orderRepository.findById(id)).thenReturn(Optional.of(order));
		when(orderRepository.save(order)).thenReturn(order);
		OrderResponseDTO response = service.updateOrderStatus(id, OrderStatus.SHIPPED);
		
		assertEquals(212, response.getQuantity());
		assertEquals(130L, response.getId());
	}
}
