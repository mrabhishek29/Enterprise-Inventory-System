package com.abhishek.enterprise_inventory_system.service;

import java.util.List;

import com.abhishek.enterprise_inventory_system.dto.OrderRequestDTO;
import com.abhishek.enterprise_inventory_system.dto.OrderResponseDTO;
import com.abhishek.enterprise_inventory_system.entity.OrderStatus;

public interface OrderService {

	OrderResponseDTO createOrder(OrderRequestDTO orderRequest);
	List<OrderResponseDTO> getAllOrders();
	OrderResponseDTO getOrderByID(Long id);
	OrderResponseDTO updateOrderStatus(Long id, OrderStatus status);
	void cancelOrder(Long Id);
}
