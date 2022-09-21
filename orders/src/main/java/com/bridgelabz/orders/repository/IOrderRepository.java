package com.bridgelabz.orders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.orders.model.OrderModel;

/**
 * Purpose:Creating repository for order
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface IOrderRepository extends JpaRepository<OrderModel, Long> {

	Optional<OrderModel> findByUserId(Long usersId);

	@Query(value = "select * from order_details where user_Id =:usersId ", nativeQuery = true)
	List<OrderModel> findByUsersId(Long usersId);

	@Query(value = "select * from order_details where is_Cancle =false ", nativeQuery = true)
	List<OrderModel> findAll();

}
