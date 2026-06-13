package com.abhishek.enterprise_inventory_system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.enterprise_inventory_system.dto.OrderRequestDTO;
import com.abhishek.enterprise_inventory_system.dto.OrderResponseDTO;
import com.abhishek.enterprise_inventory_system.entity.OrderStatus;
import com.abhishek.enterprise_inventory_system.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Orders API")
@RestController
@RequestMapping("/orders")
public class OrderController {

	public final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;	
	}
	
	@GetMapping("/all")
	@Operation(summary = "Get all orders")
	public List<OrderResponseDTO> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get Order by ID")
	public OrderResponseDTO getOrderbyId(@PathVariable Long id) {
		return orderService.getOrderByID(id);
	}
	
	@PostMapping("/create")
	public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO request) {
		return orderService.createOrder(request);
	}
	
	@Operation(summary = "update order status")
	@PatchMapping("/{id}/status")
	public OrderResponseDTO updateOrder(@PathVariable Long id, @RequestParam OrderStatus status) {
		return orderService.updateOrderStatus(id, status);
	}
	
	@Operation(summary = "order cancel")
	@DeleteMapping("/{id}")
	public void cancelOrder(@PathVariable Long id) {
		orderService.cancelOrder(id);
		
	}
	
}
