package com.bridgelabz.orders.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating DTO for order
 * 
 * @author Manoj
 * @Param all the required variables to view in web page Version 1.0
 */
@Data
public class OrderDTO {

	@NotNull(message = "bookId Should Not be Null")
	private Long bookId;
	@NotNull(message = "quantity of Birth Should Not be Null")
	private int quantity;
}
