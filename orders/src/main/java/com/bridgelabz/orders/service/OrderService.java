package com.bridgelabz.orders.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bridgelabz.orders.dto.OrderDTO;
import com.bridgelabz.orders.exception.CustomExceptions;
import com.bridgelabz.orders.model.AddressModel;
import com.bridgelabz.orders.model.OrderModel;
import com.bridgelabz.orders.repository.IAddressRepository;
import com.bridgelabz.orders.repository.IOrderRepository;
import com.bridgelabz.orders.utill.BookResponse;
import com.bridgelabz.orders.utill.ResponseClass;
import com.bridgelabz.orders.utill.TokenUtill;

/**
 * Purpose:Creating Service for user
 * 
 * @author Manoj
 * @Param business logic is present here Version 1.0
 */
@Service
public class OrderService implements IOrderService {

	@Autowired
	IOrderRepository iOrderRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	MailService mailService;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	IAddressRepository iAddressRepository;

	/**
	 * Purpose:Creating method to place order
	 * 
	 * @author Manoj
	 * @Param dto,token
	 */
	@Override
	public ResponseClass placeOrder(String token, List<Long> addressId, @Valid OrderDTO orderDto, Long cartsId) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token, Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			OrderModel orderModel = new OrderModel(orderDto);
			BookResponse isBookPresent = restTemplate.getForObject(System.getenv("findBook") + orderDto.getBookId(),
					BookResponse.class);
			if (!isBookPresent.equals(null) & isBookPresent.getBooks().getQuantity() < orderDto.getQuantity()) {
				double total = isBookPresent.getBooks().getPrice() * orderDto.getQuantity();
				orderModel.setOrderDate(LocalDate.now());
				orderModel.setPrice(total);
				orderModel.setUserId(usersId);
				orderModel.setCancel(false);

				Object isCartPresent = restTemplate.getForObject("http://cart-service/cart/cartCheck/" + cartsId,
						Object.class);
				if (isCartPresent != null) {
					orderModel.setCartId(cartsId);
				}

				List<AddressModel> addressList = new ArrayList<>();
				addressId.stream().forEach(addId -> {
					Optional<AddressModel> isAddressPresent = iAddressRepository.findById(addId);
					if (isAddressPresent.isPresent()) {
						addressList.add(isAddressPresent.get());
					}
				});

				orderModel.setAddress(addressList);
				iOrderRepository.save(orderModel);

				Long id = orderModel.getBookId();
				int quantity = orderModel.getQuantity();
				restTemplate.put("https://book-service/books/updateQuantity/" + id + "?newQuantity=" + quantity,
						ResponseClass.class);

				restTemplate.delete("http://cart-service/cart/removeCart/" + cartsId);

				return new ResponseClass(200, "success", orderModel);
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to cancle order
	 * 
	 * @author Manoj
	 * @Param id,token
	 */
	@Override
	public ResponseClass cancleOrder(String token, Long orderId) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token, Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<OrderModel> isUseridPresent = iOrderRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<OrderModel> isOrderIdPresent = iOrderRepository.findById(orderId);
				if (isOrderIdPresent.isPresent()) {
					isOrderIdPresent.get().setCancel(true);
					iOrderRepository.save(isOrderIdPresent.get());

					Long id = isOrderIdPresent.get().getBookId();
					int quantity = isOrderIdPresent.get().getQuantity();
					restTemplate.put("https://book-service/books/retrieveQuantity/" + id + "?newQuantity=" + quantity,
							ResponseClass.class);
					return new ResponseClass(200, "success", isOrderIdPresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get List of user orders
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<OrderModel> getList(String token) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token, Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			List<OrderModel> getList = iOrderRepository.findByUsersId(usersId);
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
	public List<OrderModel> getAllList(String token) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token, Boolean.class);
		if (isUserPresent) {
			List<OrderModel> getList = iOrderRepository.findAll();
			if (getList.size() > 0) {
				return getList;
			}
		}
		throw new CustomExceptions(400, "no orders");
	}
}
