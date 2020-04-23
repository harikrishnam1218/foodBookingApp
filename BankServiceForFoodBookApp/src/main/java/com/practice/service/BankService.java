package com.practice.service;

import java.util.List;


import com.practice.exception.CustomerNotFoundException;
import com.practice.exception.DBException;
import com.practice.exception.TransferException;
import com.practice.model.Account;
import com.practice.model.Customer;
import com.practice.model.CustomerData;
import com.practice.model.Transaction;
import com.practice.model.TransferBalanceRequest;



public interface BankService {


	 Long registerCustomer(CustomerData customer) throws DBException ;
	 
	 Customer getCustomerDataByMobile(Long mobile);
	 
	 List<Transaction> amountTransfer(TransferBalanceRequest request) throws TransferException;
	 
	 Account getAccountDetailsByMobile(Long mobile) throws CustomerNotFoundException;
	 
	 Account getAccountByNo(Long accountNo) throws  CustomerNotFoundException;
}
