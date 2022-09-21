package com.bridgelabz.cart.service;

import java.util.List;
import javax.validation.Valid;
import com.bridgelabz.cart.dto.CartDTO;
import com.bridgelabz.cart.model.CartModel;
import com.bridgelabz.cart.utill.ResponseClass;

/**
 * Purpose:Creating interface for note
 * 
 * @author Manoj Version 1.0
 */
public interface ICartService {

	ResponseClass addCart(String token, Long bookId, @Valid CartDTO cartDto);

	ResponseClass updateQuantity(String token, Long cartId, int newUserQuantity);

	ResponseClass deleteCart(String token, Long cartId);

	List<CartModel> getList(String token);

	List<CartModel> getAllList();

}
