package com.bridgelabz.cart.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating DTO for cart
 * 
 * @author Manoj
 * @Param all the required variables to view in web page Version 1.0
 */
@Data
public class CartDTO {
	
	@NotNull(message = "userQuantity Should Not be Null")
	private int userQuantity;
}
