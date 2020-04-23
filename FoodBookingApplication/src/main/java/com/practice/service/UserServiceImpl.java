package com.practice.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.exception.DBException;
import com.practice.model.HungerBoxUser;
import com.practice.model.UserRequest;
import com.practice.repositoty.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;

	@Override
	public HungerBoxUser registerUser(UserRequest request) throws DBException {
		HungerBoxUser user=dataSetHungerBox(request);
		HungerBoxUser userEntity=userRepo.save(user);
		if(Objects.isNull(userEntity) && Objects.isNull(userEntity.getHid())) {
			throw new DBException("HungerBox User Registration has been Failed ");
		}
		return userEntity;
	}

	private HungerBoxUser dataSetHungerBox(UserRequest request) {
		HungerBoxUser user=new HungerBoxUser();
		user.setName(request.getName());
		user.setGender(request.getGender());
		user.setPhone(request.getPhone());
		user.setCity(request.getCity());
		user.setEmail(request.getEmail());
		user.setCafe(request.getCafe());
		return user;
	}
	
	
}
