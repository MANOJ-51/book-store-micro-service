package com.bridgelabz.userregistration.config;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bridgelabz.userregistration.model.UserModel;
import com.bridgelabz.userregistration.repository.IUserRepository;
import com.bridgelabz.userregistration.service.MailService;

/**
 * Purpose:Creating configuration for scheduling
 * 
 * @author Manoj
 * @Param  Version 1.0
 */
@Component
public class SchedulingConfiguration {

	@Autowired
	IUserRepository iUserRepository;
	
	@Autowired
	MailService mailService;
	
	/**
	 * Purpose:Creating method for email Scheduling
	 * 
	 * @author Manoj
	 * 
	 * @Param  Version 1.0
	 */
	@Scheduled(fixedDelay = 999999999)
	public void emailScheudleJob () {
		List<UserModel>userList = iUserRepository.findAll();
		for(UserModel userModel:userList) {
			LocalDate expiry = userModel.getExpiryDate();
			LocalDate currentDate = LocalDate.now();
			LocalDate removeDays = expiry.minusDays(10);
			
			if(removeDays == currentDate) {
				String body = "your subscription is about to expire with in 10 days with id :-" + userModel.getUserId();
				String subject = "subscription about to expire";
				mailService.send(userModel.getEmail(), body, subject);
			}else if (expiry == currentDate) {
				String body = "dear user your plan is expired with id :-" + userModel.getUserId();
				String subject = " user subscription experied";
				mailService.send(userModel.getEmail(), body, subject);
			}
		}
	}
}
