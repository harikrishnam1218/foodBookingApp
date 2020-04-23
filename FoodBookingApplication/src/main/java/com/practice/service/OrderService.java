package com.practice.service;

import java.util.List;

import com.practice.exception.CustomerNotFoundException;
import com.practice.exception.DBException;
import com.practice.exception.TransferException;
import com.practice.exception.UserNotFoundException;
import com.practice.model.Account;
import com.practice.model.Order;
import com.practice.model.OrderRequest;

public interface OrderService {

	Order placeOrder(OrderRequest request) throws UserNotFoundException, DBException, TransferException;
	
	Account getAccount(Long accountNo) throws CustomerNotFoundException;
	
	List<Order> fetchOrderHistory() throws DBException;
	
	String getPortNo();
}
