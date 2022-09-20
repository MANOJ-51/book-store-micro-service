package com.bridgelabz.userregistration.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.userregistration.model.UserModel;

/**
 * Purpose:Creating repository for user
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */

public interface IUserRepository extends JpaRepository<UserModel, Long>{

	Optional<UserModel> findByEmail(String emailId);
}
