package com.bridgelabz.cart.service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bridgelabz.cart.dto.CartDTO;
import com.bridgelabz.cart.exception.CustomExceptions;
import com.bridgelabz.cart.model.CartModel;
import com.bridgelabz.cart.repository.ICartRepository;
import com.bridgelabz.cart.utill.BookResponse;
import com.bridgelabz.cart.utill.ResponseClass;
import com.bridgelabz.cart.utill.TokenUtill;

/**
 * Purpose:Creating Service for cart
 * 
 * @author Manoj
 * @Param business logic is present here Version 1.0
 */
@Service
public class CartService implements ICartService {

	@Autowired
	ICartRepository iCartRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Purpose:Creating method to add to cart
	 * @author Manoj
	 * @Param dto,token
	 */
	@Override
	public ResponseClass addCart(String token, Long bookId, @Valid CartDTO cartDto) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token, Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			BookResponse isBookPresent = restTemplate.getForObject(System.getenv("findBook") + bookId,
					BookResponse.class);
			if (!isBookPresent.equals(null)) {
				CartModel cartModel = new CartModel(cartDto);
				cartModel.setUserId(usersId);
				iCartRepository.save(cartModel);
				double total = isBookPresent.getBooks().getPrice() * cartModel.getUserQuantity();
				cartModel.setTotalPrice(total);
				return new ResponseClass(200, "success", cartModel);
			}
		}

		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to addQuantity
	 * 
	 * @author Manoj
	 * @Param token, cartId, newUserQuantity
	 */
	@Override
	public ResponseClass updateQuantity(String token, Long cartId, int newUserQuantity) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token, Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<CartModel> isUseridPresent = iCartRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<CartModel> isCartIdPresent = iCartRepository.findById(cartId);
				if (isCartIdPresent.isPresent()) {
					isCartIdPresent.get().setUserQuantity(newUserQuantity);
					iCartRepository.save(isCartIdPresent.get());
					BookResponse isBookPresent = restTemplate
							.getForObject(System.getenv("findBook") + isCartIdPresent.get().getBookId(), BookResponse.class);
					if (!isBookPresent.equals(null)) {
						double total = isCartIdPresent.get().getUserQuantity() * isBookPresent.getBooks().getPrice();
						isCartIdPresent.get().setTotalPrice(total);
						iCartRepository.save(isCartIdPresent.get());
						return new ResponseClass(200, "success", isCartIdPresent.get());
					}
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to delete cart
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass deleteCart(String token, Long cartId) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token, Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<CartModel> isUseridPresent = iCartRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<CartModel> isCartIdPresent = iCartRepository.findById(cartId);
				if (isCartIdPresent.isPresent()) {
					iCartRepository.delete(isCartIdPresent.get());
					return new ResponseClass(200, "success", isCartIdPresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get List of user cart
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<CartModel> getList(String token) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token, Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			List<CartModel> getList = iCartRepository.findByUsersId(usersId);
			if (getList.size() > 0) {
				return getList;
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get List of all cart
	 * 
	 * @author Manoj
	 * @Param 
	 */
	@Override
	public List<CartModel> getAllList() {
		List<CartModel> getList = iCartRepository.findAll();
		if (getList.size() > 0) {
			return getList;
		}
		throw new CustomExceptions(400, "no items in cart");
	}
}
