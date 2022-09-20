package com.bridgelabz.userregistration.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.userregistration.dto.UserDTO;
import com.bridgelabz.userregistration.exceptions.CustomExceptions;
import com.bridgelabz.userregistration.model.UserModel;
import com.bridgelabz.userregistration.repository.IUserRepository;
import com.bridgelabz.userregistration.utill.ResponseClass;
import com.bridgelabz.userregistration.utill.TokenUtill;

/**
 * Purpose:Creating Service for user
 * 
 * @author Manoj
 * @Param business logic is present here Version 1.0
 */
@Service
public class UserService implements IUserService {

	@Autowired
	IUserRepository iUserRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	MailService mailService;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Purpose:Creating method to add user
	 * 
	 * @author Manoj
	 * @Param user dto
	 */
	@Override
	public ResponseClass createUser(@Valid UserDTO userDTO) {
		UserModel userModel = new UserModel(userDTO);
		userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userModel.setCreatedAt(LocalDateTime.now());
		userModel.setVerify(false);
		userModel.setIsDelete(false);
		iUserRepository.save(userModel);
		String body = "User Registration Is Successful with id :-" + userModel.getUserId() + "\n" + userModel;
		String subject = "User Registration Success";
		mailService.send(userModel.getEmail(), body, subject);
		return new ResponseClass(200, "success", userModel);
	}

	/**
	 * Purpose:Creating method to Update user
	 * 
	 * @author Manoj
	 * @Param id ,token,name,email,phone number
	 */
	@Override
	public ResponseClass updateUser(String token, String firstName, String lastName, String kyc, String email,
			String dateOfBirth) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserPresent = iUserRepository.findById(usersId);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setFirstName(firstName);
			isUserPresent.get().setLastName(lastName);
			isUserPresent.get().setKyc(kyc);
			isUserPresent.get().setEmail(email);
			isUserPresent.get().setDateOfBirth(dateOfBirth);
			isUserPresent.get().setUpdatedAt(LocalDateTime.now());
			iUserRepository.save(isUserPresent.get());
			String body = "User Update Is Successful with id :-" + isUserPresent.get().getUserId() + "\n"
					+ isUserPresent.get();
			String subject = "User Update Success";
			mailService.send(isUserPresent.get().getEmail(), body, subject);
			return new ResponseClass(200, "success", isUserPresent.get());
		}
		throw new CustomExceptions(400, "User Not Found");
	}

	/**
	 * Purpose:Creating method to getList
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<UserModel> getList(String token) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserIdPresent = iUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			List<UserModel> getData = iUserRepository.findAll();
			if (getData.size() > 0) {
				return getData;
			}
		}
		throw new CustomExceptions(400, "User Not Found");
	}

	/**
	 * Purpose:Creating method to delete user
	 * 
	 * @author Manoj
	 * @Param id ,token
	 */
	@Override
	public ResponseClass deleteUser(String token, Long userId) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserIdPresent = iUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
			if (isUserPresent.isPresent()) {
				isUserPresent.get().setIsDelete(true);
				iUserRepository.save(isUserPresent.get());
				String body = "User Deleted Is Successful with id :-" + isUserPresent.get().getUserId() + "\n"
						+ isUserPresent.get();
				String subject = "User Deleted Success";
				mailService.send(isUserPresent.get().getEmail(), body, subject);
				return new ResponseClass(200, "success", isUserPresent.get());
			}
		}
		throw new CustomExceptions(400, "User Not Found");
	}

	/**
	 * Purpose:Creating method to delete user
	 * 
	 * @author Manoj
	 * @Param token,userId
	 */
	@Override
	public ResponseClass retrieveUser(String token, Long userId) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserIdPresent = iUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
			if (isUserPresent.isPresent() && isUserPresent.get().getIsDelete() == true) {
				isUserPresent.get().setIsDelete(false);
				iUserRepository.save(isUserPresent.get());
				String body = "User retrieved Is Successful with id :-" + isUserPresent.get().getUserId() + "\n"
						+ isUserPresent.get();
				String subject = "User retrieved Success";
				mailService.send(isUserPresent.get().getEmail(), body, subject);
				return new ResponseClass(200, "success", isUserPresent.get());
			}
		}
		throw new CustomExceptions(400, "User Not Found");
	}

	/**
	 * Purpose:Creating method to delete user permanently
	 * 
	 * @author Manoj
	 * @Param token ,userId
	 */
	@Override
	public ResponseClass deleteUserPermanent(String token, Long userId) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserIdPresent = iUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
			if (isUserPresent.isPresent() && isUserPresent.get().getIsDelete() == true) {
				iUserRepository.delete(isUserPresent.get());
				return new ResponseClass(200, "success", isUserPresent.get());
			}
		}
		throw new CustomExceptions(400, "User Not Found in trash");

	}

	/**
	 * Purpose:Creating method to login token
	 * 
	 * @author Manoj
	 * @Param emailid ,password
	 */
	@Override
	public ResponseClass loginToken(String emailId, String password) {
		Optional<UserModel> isEmailPresent = iUserRepository.findByEmail(emailId);
		if (isEmailPresent.isPresent()) {
			// if (isEmailPresent.get().getPassword().equals(password)) {
			if (passwordEncoder.matches(password, isEmailPresent.get().getPassword())) {
				String token = tokenUtill.createToken(isEmailPresent.get().getUserId());
				return new ResponseClass(200, "Login Success", token);
			} else {
				throw new CustomExceptions(400, "Password is Wrong");
			}
		}
		throw new CustomExceptions(400, "No Details Matched");
	}

	/**
	 * Purpose:Creating method to reset user password
	 * 
	 * @author Manoj
	 * @Param email
	 */
	@Override
	public ResponseClass resetPassword(String email) {
		Optional<UserModel> isEmailPresent = iUserRepository.findByEmail(email);
		if (isEmailPresent.isPresent()) {
			String token = tokenUtill.createToken(isEmailPresent.get().getUserId());
			String url = System.getenv("url") + "\n" + token;
			String subject = "user reset Success";
			mailService.send(isEmailPresent.get().getEmail(), url, subject);
		}
		throw new CustomExceptions(400, "Invalid Email");
	}

	/**
	 * Purpose:Creating method to change password
	 * 
	 * @author Manoj
	 * @Param token,new password
	 */
	@Override
	public ResponseClass changePassword(String token, String newPassword) {
		Long userId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserIdPresent = iUserRepository.findById(userId);
		if (isUserIdPresent.isPresent()) {
			isUserIdPresent.get().setPassword(passwordEncoder.encode(newPassword));
			iUserRepository.save(isUserIdPresent.get());
			String body = "user Change Password Is Successful with id :-" + isUserIdPresent.get().getUserId() + "\n"
					+ isUserIdPresent.get();
			String subject = "user Change Password Success";
			mailService.send(isUserIdPresent.get().getEmail(), body, subject);
			return new ResponseClass(200, "success", isUserIdPresent.get());
		}
		throw new CustomExceptions(400, "Invalid Token");
	}

	/**
	 * Purpose:Creating method to validate user using token
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public boolean validateToken(String token) {
		Long userId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
		if (isUserPresent.isPresent()) {
			return true;
		}
		return false;
	}

	/**
	 * Purpose:Creating method to activate user using token and send otp to user
	 * through mail
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public ResponseClass activateUserByOtp(String token) {
		Long userId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
		if (isUserPresent.isPresent()) {
			int sendOtp = (int) ((Math.random() * 900000) + 100000);
			isUserPresent.get().setOtp(sendOtp);
			iUserRepository.save(isUserPresent.get());
			String body = "Otp sent Is Successful with id :-" + isUserPresent.get().getUserId() + "\n"
					+ isUserPresent.get().getOtp();
			String subject = "otp for user verification sent";
			mailService.send(isUserPresent.get().getEmail(), body, subject);
			return new ResponseClass(200, "success", isUserPresent.get());
		}
		throw new CustomExceptions(400, "Invalid Token");
	}

	/**
	 * Purpose:Creating method to activate user by otp
	 * 
	 * @author Manoj
	 * @Param token ,otp
	 */
	@Override
	public ResponseClass verifyUserByOtp(String token, int userOtp) {
		Long userId = tokenUtill.decodeToken(token);
		Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
		if (isUserPresent.isPresent()) {
			if (userOtp == isUserPresent.get().getOtp()) {
				isUserPresent.get().setVerify(true);
				iUserRepository.save(isUserPresent.get());
				String body = "user verification Is Successful with id :-" + isUserPresent.get().getUserId();
				String subject = " user verification sucess";
				mailService.send(isUserPresent.get().getEmail(), body, subject);
				return new ResponseClass(200, "success", isUserPresent.get());
			}
		}
		throw new CustomExceptions(400, "Invalid Token");
	}
}
