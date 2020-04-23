package com.practice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.exception.CustomerNotFoundException;
import com.practice.exception.DBException;
import com.practice.exception.TransferException;
import com.practice.model.Account;
import com.practice.model.CustomerData;
import com.practice.model.Transaction;
import com.practice.model.TransferBalanceRequest;
import com.practice.service.BankService;

@RestController
@RequestMapping("bank/api")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	Environment environment;
	

	@PostMapping("/registercustomer")
	public Long registerUser(@RequestBody CustomerData customerData) throws DBException {
		Long accountNumber = bankService.registerCustomer(customerData);
		return accountNumber;
	}
	
	@PostMapping("/transferamount")
	public List<Transaction> transferBalance(@RequestBody TransferBalanceRequest balanceRequest)
			throws TransferException {
		List<Transaction> transactionList = bankService.amountTransfer(balanceRequest);
		return transactionList;
	}
	
	@GetMapping("/fetchAccount")
	public Account getAccountDetail(@RequestParam("accountNo") Long accountNo) throws CustomerNotFoundException{
	  return bankService.getAccountByNo(accountNo);
	//return account;
	}
	
	@GetMapping("/fetchAccountByMobile")
	public Account getAccountDetailByMobile(@RequestParam("mobile") Long mobile) throws CustomerNotFoundException{
	  return bankService.getAccountDetailsByMobile(mobile);
	//return account;
	}
	
	@GetMapping("/fetchTransaction")
	public List<Transaction> getTransactions(){
	  //return bankService.getAccountDetailsByMobile(mobile);
	return bankService.getTransactions();
	}
	

	
	@GetMapping("/info")
	public String getInfo() {
		String port = environment.getProperty("local.server.port");
		return "From server " + port;
	}
}
