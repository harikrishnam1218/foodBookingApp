package com.practice.client;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.exception.CustomerNotFoundException;
import com.practice.exception.DBException;
import com.practice.exception.TransferException;
import com.practice.model.Account;
import com.practice.model.CustomerData;
import com.practice.model.Transaction;
import com.practice.model.TransferBalanceRequest;

//@FeignClient(name="http://BANKFOODSERVICE/bank/api/")

@FeignClient(value="BANKFOODSERVICE",url="http://localhost:8011/bank/api/")
public interface FoodBookingFeignClient {

	@PostMapping("/registercustomer")
	public Long registerUser(@RequestBody CustomerData customerData) throws DBException ;
	
	@PostMapping("/transferamount")
	public List<Transaction> transferBalance(@RequestBody TransferBalanceRequest balanceRequest);
	
	@GetMapping("/fetchAccount")
	public Account getAccountDetail(@RequestParam("accountNo") Long accountNo) throws CustomerNotFoundException;

	@GetMapping("/info")
	public String getInfo() ;
}
