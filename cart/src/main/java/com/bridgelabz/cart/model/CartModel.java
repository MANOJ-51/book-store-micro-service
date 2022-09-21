package com.bridgelabz.cart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.bridgelabz.cart.dto.CartDTO;
import lombok.Data;

/**
 * Purpose:Creating model for cart
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "cart_details")
public class CartModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long cartId;
	private Long userId;
	private Long bookId;
	private int userQuantity;
	private double totalPrice;
	
	public CartModel(CartDTO cartDTO) {
		this.userQuantity = cartDTO.getUserQuantity();
	}
	
	public CartModel() {
		super();
	}
	
}
