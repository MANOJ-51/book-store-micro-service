package com.bridgelabz.orders.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelabz.orders.dto.AddressDTO;

import lombok.Data;

/**
 * Purpose:Creating model for address
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "address_details")
public class AddressModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressId;
	private Long userId;
	private String name;
	private String phoneNumber;
	private String pinCode;
	private String address;
	private String city;
	private String landMark;
	private String type;
	@ManyToOne
	@JoinColumn(name = "order_Id")
	private OrderModel orders;

	public AddressModel(AddressDTO addressDto) {
		this.name = addressDto.getName();
		this.phoneNumber = addressDto.getPhoneNumber();
		this.pinCode = addressDto.getPinCode();
		this.address = addressDto.getAddress();
		this.city = addressDto.getCity();
		this.landMark = addressDto.getLandMark();
		this.type = addressDto.getType();
	}

	public AddressModel() {
		super();
	}

}