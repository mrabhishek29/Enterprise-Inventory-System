package com.abhishek.enterprise_inventory_system.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.enterprise_inventory_system.dto.OrderRequestDTO;
import com.abhishek.enterprise_inventory_system.dto.OrderResponseDTO;
import com.abhishek.enterprise_inventory_system.entity.Order;
import com.abhishek.enterprise_inventory_system.entity.OrderStatus;
import com.abhishek.enterprise_inventory_system.entity.Product;
import com.abhishek.enterprise_inventory_system.entity.User;
import com.abhishek.enterprise_inventory_system.exception.OutofStockException;
import com.abhishek.enterprise_inventory_system.exception.ResourceNotFoundException;
import com.abhishek.enterprise_inventory_system.repository.OrderRepository;
import com.abhishek.enterprise_inventory_system.repository.ProductRepository;
import com.abhishek.enterprise_inventory_system.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository prodRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
		
		Product product = getProductById(orderRequest.getProductId());
		
		//fetch user
		User user = getUserById(orderRequest.getUserId());
		
		validateStock(product, orderRequest.getQuantity());
		
		BigDecimal totalAmount = calculateTotalAmount(product, orderRequest.getQuantity());
		
		Order order = buildOrder(user, product, orderRequest.getQuantity(), totalAmount);
		
		updateStock(product, orderRequest.getQuantity());
		
		Order savedOrder = orderRepository.save(order);
		
		return  mapToResponse(savedOrder);
	}

	private Product getProductById(Long productId){
		return prodRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}
	
	private User getUserById(Long userId){
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}
	
	private void validateStock(Product product, Integer quantity) {
		if(product.getStock() < quantity)
			throw new OutofStockException("Out of stock");
	}
	
	private BigDecimal calculateTotalAmount(Product product, Integer quantity) {
		return BigDecimal.valueOf(product.getPrice())
				.multiply(BigDecimal.valueOf(quantity));
	}
	
	private Order buildOrder(User user, Product product, Integer quantity, BigDecimal totalPrice){
		Order order = new Order();
		order.setQuantity(quantity);
		order.setStatus(OrderStatus.CONFIRMED);
		order.setTotalPrice(totalPrice);
		order.setProduct(product);
		order.setUser(user);
		return order;
	}
	
	private void updateStock(Product product, Integer orderQuantity) {
		Integer finalStock = product.getStock() - orderQuantity;
		product.setStock(finalStock);
		prodRepository.save(product);
	}
	
	private OrderResponseDTO mapToResponse(Order savedOrder) {
		OrderResponseDTO response = new OrderResponseDTO();
		response.setId(savedOrder.getId());
		response.setQuantity(savedOrder.getQuantity());
		response.setProductName(savedOrder.getProduct().getName());
		response.setTotalPrice(savedOrder.getTotalPrice());
		response.setUserId(savedOrder.getUser().getId());
		response.setProductId(savedOrder.getProduct().getId());
		return response;
	}
	
	@Override
	public List<OrderResponseDTO> getAllOrders() {
		return orderRepository.findAll()
		.stream()
		.map(this::mapToResponse)
		.toList();
	}

	@Override
	public OrderResponseDTO getOrderByID(Long Id) {
		Order order = orderRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("order not found"));
		return mapToResponse(order);
	}

	@Override
	public void cancelOrder(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order not found"));
		
		Product product = order.getProduct();
		product.setStock(product.getStock() + order.getQuantity());
		prodRepository.save(product);
		
		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);	
	}

	@Override
	public OrderResponseDTO updateOrderStatus(Long id, OrderStatus status) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order not found"));
		order.setStatus(status);
		orderRepository.save(order);
		return mapToResponse(order);
	}

}
