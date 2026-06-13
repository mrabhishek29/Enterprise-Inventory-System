package com.abhishek.enterprise_inventory_system.dto;

import jakarta.validation.constraints.NotNull;

public class OrderRequestDTO {
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Integer quantity;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}