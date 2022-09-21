package com.bridgelabz.cart.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.cart.dto.CartDTO;
import com.bridgelabz.cart.model.CartModel;
import com.bridgelabz.cart.service.ICartService;
import com.bridgelabz.cart.utill.ResponseClass;

/**
 * Purpose:Creating APIS for cart Controller
 * 
 * @author Manoj
 * @Param Http METHODS Version 1.0
 */
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	ICartService iCartService;

	/**
	 * Purpose:Creating method to add to cart
	 * @author Manoj
	 * @Param dto,token
	 */
	@PostMapping("/addCart")
	public ResponseEntity<ResponseClass> addCart(@RequestHeader String token, @RequestParam Long bookId,
			@RequestBody @Valid CartDTO cartDto) {
		ResponseClass responseClass = iCartService.addCart(token, bookId, cartDto);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to addQuantity
	 * 
	 * @author Manoj
	 * @Param token, cartId, newUserQuantity
	 */
	@PutMapping("updateQuantity/{cartId}")
	public ResponseEntity<ResponseClass> updateQuantity(@RequestHeader String token, @PathVariable Long cartId,
			@RequestParam int newUserQuantity) {
		ResponseClass responseClass = iCartService.updateQuantity(token, cartId, newUserQuantity);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to delete cart
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@DeleteMapping("/deleteCart/{cartId}")
	public ResponseEntity<ResponseClass> deleteCart(@RequestHeader String token, @PathVariable Long cartId) {
		ResponseClass responseClass = iCartService.deleteCart(token, cartId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to get List of user cart
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/cartUserList")
	public ResponseEntity<List<?>> getList(@RequestHeader String token) {
		List<CartModel> responseClass = iCartService.getList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to get List of all cart
	 * 
	 * @author Manoj
	 * @Param 
	 */
	@GetMapping("/cartList")
	public ResponseEntity<List<?>> getAllList() {
		List<CartModel> responseClass = iCartService.getAllList();
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
}
