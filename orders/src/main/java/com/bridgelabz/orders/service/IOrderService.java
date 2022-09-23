package com.bridgelabz.orders.service;

import java.util.List;
import com.bridgelabz.orders.model.OrderModel;
import com.bridgelabz.orders.utill.ResponseClass;

/**
 * Purpose:Creating APIS for user Controller
 * 
 * @author Manoj
 * @Param abstract METHODS Version 1.0
 */
public interface IOrderService {

	ResponseClass placeOrder(String token, List<Long> addressId, Long cartsId);

	ResponseClass cancleOrder(String token, Long orderId);

	List<OrderModel> getList(String token);

	List<OrderModel> getAllList(String token);

}
