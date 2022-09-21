package com.bridgelabz.orders.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.orders.dto.AddressDTO;
import com.bridgelabz.orders.model.AddressModel;
import com.bridgelabz.orders.service.IAddressService;
import com.bridgelabz.orders.utill.ResponseClass;

/**
 * Purpose:Creating APIS for address Controller
 * 
 * @author Manoj
 * @Param Http METHODS Version 1.0
 */
@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	IAddressService iAddressService;

	/**
	 * Purpose:Creating method to add address
	 * 
	 * @author Manoj
	 * @Param token, dto
	 */
	@PostMapping("/createAddress")
	public ResponseEntity<ResponseClass> createAddress(@RequestHeader String token,
			@RequestBody @Valid AddressDTO addressDTO) {
		ResponseClass responseClass = iAddressService.createAddress(token, addressDTO);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to update address
	 * 
	 * @author Manoj
	 * @Param token,addressId
	 */
	@PutMapping("update/{addressId}")
	public ResponseEntity<ResponseClass> updateAddress(@RequestHeader String token, @PathVariable Long addressId,
			@RequestBody @Valid AddressDTO addressDTO) {
		ResponseClass responseClass = iAddressService.updateAddress(token, addressId, addressDTO);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of address
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/addressList")
	public ResponseEntity<List<?>> getList(@RequestHeader String token) {
		List<AddressModel> responseClass = iAddressService.getList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to delete address
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<ResponseClass> deleteNote(@RequestHeader String token, @PathVariable Long addressId) {
		ResponseClass responseClass = iAddressService.deleteNote(token, addressId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
}
