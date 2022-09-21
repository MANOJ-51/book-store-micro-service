package com.bridgelabz.orders.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.orders.dto.OrderDTO;
import com.bridgelabz.orders.model.OrderModel;
import com.bridgelabz.orders.service.IOrderService;
import com.bridgelabz.orders.utill.ResponseClass;

/**
 * Purpose:Creating APIS for user Controller
 * 
 * @author Manoj
 * @Param Http METHODS Version 1.0
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	IOrderService iOrderService;

	/**
	 * Purpose:Creating method to place order
	 * 
	 * @author Manoj
	 * @Param dto,token
	 */
	@PostMapping("/placeOrder")
	public ResponseEntity<ResponseClass> placeOrder(@RequestHeader String token,@RequestParam List<Long> addressId,
			@RequestBody @Valid OrderDTO orderDto) {
		ResponseClass responseClass = iOrderService.placeOrder(token,addressId, orderDto);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to cancle order
	 * 
	 * @author Manoj
	 * @Param id,token
	 */
	@PutMapping("/cancleOrder/{orderId}")
	public ResponseEntity<ResponseClass> cancleOrder(@RequestHeader String token, @PathVariable Long orderId) {
		ResponseClass responseClass = iOrderService.cancleOrder(token, orderId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of user orders
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/orderUserList")
	public ResponseEntity<List<?>> getList(@RequestHeader String token) {
		List<OrderModel> responseClass = iOrderService.getList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of all cart
	 * 
	 * @author Manoj
	 * @Param
	 */
	@GetMapping("/orderList")
	public ResponseEntity<List<?>> getAllList() {
		List<OrderModel> responseClass = iOrderService.getAllList();
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
}
