package com.bridgelabz.userregistration.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.userregistration.dto.UserDTO;

import lombok.Data;

/**
 * Purpose:Creating model for user
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "user_details")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String firstName;
	private String lastName;
	private String kyc;
	private String email;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean verify;
	private Boolean isDelete;
	private String dateOfBirth;
	private int otp;
	private LocalDate purchaseDate;
	private LocalDate expiryDate;

	public UserModel(UserDTO userDTO) {
		this.firstName = userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.kyc = userDTO.getKyc();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.dateOfBirth = userDTO.getDateOfBirth();
	}

	public UserModel() {

	}
}
