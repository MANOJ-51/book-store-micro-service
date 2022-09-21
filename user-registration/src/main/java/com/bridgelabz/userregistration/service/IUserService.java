package com.bridgelabz.userregistration.service;

import java.util.List;
import javax.validation.Valid;
import com.bridgelabz.userregistration.dto.UserDTO;
import com.bridgelabz.userregistration.model.UserModel;
import com.bridgelabz.userregistration.utill.ResponseClass;

/**
 * Purpose:Creating APIS for user Controller
 * 
 * @author Manoj
 * @Param abstract METHODS Version 1.0
 */
public interface IUserService {

	ResponseClass createUser(@Valid UserDTO userDTO);

	ResponseClass updateUser(String token, String firstName, String lastName, String kyc, String email,
			String dateOfBirth);

	List<UserModel> getList(String token);

	ResponseClass deleteUser(String token, Long userId);

	ResponseClass retrieveUser(String token, Long userId);

	ResponseClass deleteUserPermanent(String token, Long userId);

	ResponseClass loginToken(String emailId, String password);

	ResponseClass resetPassword(String email);

	ResponseClass changePassword(String token, String newPassword);

	boolean validateToken(String token);

	ResponseClass activateUserByOtp(String token);

	ResponseClass verifyUserByOtp(String token, int userOtp);

	ResponseClass buySubscprition(String token);
}
