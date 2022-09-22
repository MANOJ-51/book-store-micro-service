package com.bridgelabz.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.cart.model.CartModel;

/**
 * Purpose:Creating repository for cart
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface ICartRepository extends JpaRepository<CartModel, Long> {

	@Query(value = "select * from cart_details where user_Id =:usersId ", nativeQuery = true)
	Optional<CartModel> findByUserId(Long usersId);

	@Query(value = "select * from cart_details where user_Id =:usersId ", nativeQuery = true)
	List<CartModel> findByUsersId(Long usersId);

}
