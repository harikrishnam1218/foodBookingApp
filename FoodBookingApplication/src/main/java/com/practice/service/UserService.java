package com.practice.service;

import com.practice.exception.DBException;
import com.practice.model.HungerBoxUser;
import com.practice.model.UserRequest;

public interface UserService {

	HungerBoxUser registerUser( UserRequest request) throws DBException;
}
