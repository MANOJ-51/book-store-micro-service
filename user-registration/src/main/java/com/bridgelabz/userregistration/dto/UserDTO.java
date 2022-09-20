package com.bridgelabz.userregistration.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating DTO for  User
 * 
 * @author Manoj
 * @Param all the required variables to view in web page Version 1.0
 */
@Data
public class UserDTO {

	@NotNull(message = "firstName Should Not be Null")
	private String firstName;
	@NotNull(message = "lastName Should Not be Null")
	private String lastName;
	@NotNull(message = "kyc Should Not be Null")
	private String kyc;
	@NotNull(message = "Email Should Not be Null")
	private String email;
	@NotNull(message = "Password Should Not be Null")
	private String password;
	@NotNull(message = "Date of Birth Should Not be Null")
	private String dateOfBirth;
}
