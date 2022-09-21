package com.bridgelabz.orders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.orders.model.AddressModel;


/**
 * Purpose:Creating repository for address
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface IAddressRepository extends JpaRepository<AddressModel, Long> {

	Optional<AddressModel> findByUserId(Long usersId);

	@Query(value = "select * from address_details where user_Id =:usersId ", nativeQuery = true)
	List<AddressModel> findByUsersId(Long usersId);

}
