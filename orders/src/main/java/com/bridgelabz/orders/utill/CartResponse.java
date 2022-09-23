package com.bridgelabz.orders.utill;

import com.bridgelabz.orders.dto.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Purpose:Creating Response class
 * 
 * @author Manoj
 * @Param errorMessage,ErrorMessage,token. Version 1.0
 */
@Data
@AllArgsConstructor
public class CartResponse {

	private int errorCode;
	private String errorMessage;
	private CartDTO cart;
	
	public CartResponse() {
		super();
	}
}