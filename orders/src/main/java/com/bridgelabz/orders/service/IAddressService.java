package com.bridgelabz.orders.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.orders.dto.AddressDTO;
import com.bridgelabz.orders.model.AddressModel;
import com.bridgelabz.orders.utill.ResponseClass;

/**
 * Purpose:abstract methods of service
 * 
 * @author Manoj
 * @Param abstract METHODS Version 1.0
 */
public interface IAddressService {

	ResponseClass createAddress(String token, @Valid AddressDTO addressDTO);

	ResponseClass updateAddress(String token, Long addressId, @Valid AddressDTO addressDTO);

	List<AddressModel> getList(String token);

	ResponseClass deleteNote(String token, Long addressId);

}
