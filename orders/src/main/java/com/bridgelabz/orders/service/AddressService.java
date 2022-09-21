package com.bridgelabz.orders.service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bridgelabz.orders.dto.AddressDTO;
import com.bridgelabz.orders.exception.CustomExceptions;
import com.bridgelabz.orders.model.AddressModel;
import com.bridgelabz.orders.repository.IAddressRepository;
import com.bridgelabz.orders.utill.ResponseClass;
import com.bridgelabz.orders.utill.TokenUtill;

/*
* Purpose:Creating Service for user
* 
* @author Manoj
* @Param business logic is present here Version 1.0
*/
@Service
public class AddressService implements IAddressService {

	@Autowired
	IAddressRepository iAddressRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Purpose:Creating method to add address
	 * 
	 * @author Manoj
	 * @Param token, dto
	 */
	@Override
	public ResponseClass createAddress(String token, @Valid AddressDTO addressDTO) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			AddressModel addressModel = new AddressModel(addressDTO);
			addressModel.setUserId(usersId);
			iAddressRepository.save(addressModel);
			return new ResponseClass(200, "success", addressModel);
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to update address
	 * 
	 * @author Manoj
	 * @Param token,addressId
	 */
	@Override
	public ResponseClass updateAddress(String token, Long addressId, @Valid AddressDTO addressDTO) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<AddressModel> isUseridPresent = iAddressRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<AddressModel> isAddressPresent = iAddressRepository.findById(addressId);
				if (isAddressPresent.isPresent()) {
					isAddressPresent.get().setName(addressDTO.getName());
					isAddressPresent.get().setPhoneNumber(addressDTO.getPhoneNumber());
					isAddressPresent.get().setPinCode(addressDTO.getPinCode());
					isAddressPresent.get().setAddress(addressDTO.getAddress());
					isAddressPresent.get().setCity(addressDTO.getCity());
					isAddressPresent.get().setType(addressDTO.getType());
					isAddressPresent.get().setLandMark(addressDTO.getLandMark());
					iAddressRepository.save(isAddressPresent.get());
					return new ResponseClass(200, "success", isAddressPresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get List of address
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<AddressModel> getList(String token) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			List<AddressModel> getList = iAddressRepository.findByUsersId(usersId);
			if (getList.size() > 0) {
				return getList;
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to delete address
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass deleteNote(String token, Long addressId) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("tokenCheck") + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<AddressModel> isUseridPresent = iAddressRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<AddressModel> isAddressPresent = iAddressRepository.findById(addressId);
				if (isAddressPresent.isPresent()) {
					iAddressRepository.delete(isAddressPresent.get());
					return new ResponseClass(200, "success", isAddressPresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token in not valid");
	}

}
