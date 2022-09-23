package com.bridgelabz.orders.dto;

import lombok.Data;

@Data
public class CartDTO {
	private Long bookId;
	private int userQuantity;
	private double totalPrice;
}
