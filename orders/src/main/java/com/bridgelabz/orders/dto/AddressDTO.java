package com.bridgelabz.orders.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Purpose:Creating DTO for address
 * 
 * @author Manoj
 * @Param all the required variables to view in web page Version 1.0
 */
@Data
public class AddressDTO {

	@NotNull(message = "Name Should Not be Null")
	private String name;
	@NotNull(message = "phone number Should Not be Null")
	private String phoneNumber;
	@NotNull(message = "pincode Should Not be Null")
	private String pinCode;
	@NotNull(message = "address Should Not be Null")
	private String address;
	@NotNull(message = "city Should Not be Null")
	private String city;
	@NotNull(message = "landmark Should Not be Null")
	private String landMark;
	@NotNull(message = "type Should Not be Null")
	private String type;
	
}