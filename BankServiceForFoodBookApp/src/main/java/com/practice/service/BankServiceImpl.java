package com.practice.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.exception.CustomerNotFoundException;
import com.practice.exception.DBException;
import com.practice.exception.TransferException;
import com.practice.model.Account;
import com.practice.model.Customer;
import com.practice.model.CustomerData;
import com.practice.model.Transaction;
import com.practice.model.TransferBalanceRequest;
import com.practice.repository.AccountRepository;
import com.practice.repository.CustomerRepository;
import com.practice.repository.TransactionRepository;

@Service
public class BankServiceImpl implements BankService {
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	@Override
	public Long registerCustomer(CustomerData data) throws DBException {
		Account accountData=null;
		Customer userData = customerData(data);
		Customer user=customerRepo.save(userData);
		if(Objects.nonNull(user) && Objects.nonNull(user.getCid())) {
			Account account = accountData(data, user);
			accountData=accountRepo.save(account);
		if(Objects.isNull(accountData) && Objects.isNull(accountData.getAid())){
			{
				throw new DBException("Account Data not saved..");
			}
		}
		}else {
			throw new DBException("User Data not Saved ...");
		}
		Transaction trasaction=new Transaction(accountData.getBalance(),new Timestamp(System.currentTimeMillis()),accountData.getAccontno());
		trasaction.setMessage(accountData.getBalance()+" has been Credited to "+accountData.getAccontno());
		transactionRepo.save(trasaction);
		
		return accountData.getAccontno();
	}

	private Account accountData(CustomerData data, Customer user) {
		Account account=new Account();
		account.setBalance(data.getBalance());
		account.setAccontno(ramdomAccountNumber()); 
		account.setIfsccode(data.getIfsc());
		account.setName(data.getBankname());
		account.setType(data.getBanktype());
		account.setCustomer(user);
		account.setCardno(ramdomCardNumber());
		account.setCvv(ramdomCvvNumber());
		account.setExpireDate(expiryDate());
		return account;
	}

	private Customer customerData(CustomerData data) {
		Customer userData=new Customer();
		userData.setAadhar(data.getAadhar());
		userData.setAge(data.getAge());
		userData.setMobile(data.getMobile());
		userData.setDob(data.getDob());
		userData.setFname(data.getFname());
		userData.setGender(data.getGender());
		userData.setLname(data.getLname());
		userData.setPan(data.getPan());
		return userData;
	}
	private Long ramdomAccountNumber() {
		Random rand = new Random();
		long x = (long)(rand.nextDouble()*1000000000000L);
		return x;
	}
	
	private Integer ramdomCvvNumber() {
		Random rand = new Random();
		Integer x = (int) (rand.nextDouble()*1000);
		return x;
	}
	
	private Long ramdomCardNumber() {
		Random rand = new Random();
		long x = (long)(rand.nextDouble()*10000000000000000L);
		return x;
	}
	

	private Date expiryDate() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 6);
		java.util.Date expirationDate = cal.getTime();
		return expirationDate;
	}

	@Override
	public Customer getCustomerDataByMobile(Long mobile) {
		return customerRepo.getByMobile(mobile);
	}

	@Override
	public Account getAccountDetailsByMobile(Long mobile) throws CustomerNotFoundException {
		Customer cust=getCustomerDataByMobile(mobile);
		if(Objects.isNull(cust) && Objects.isNull(cust.getCid())){
			throw new CustomerNotFoundException("Customer Details was not avaialble for this number");
		} 
		return accountRepo.getAccountDetailsByMobile(mobile);
		
	}

	@Override
	public List<Transaction> amountTransfer(TransferBalanceRequest request) throws TransferException {

		List<Transaction> transationList= new ArrayList<>();
	/*	
		Integer from=request.getFromPhone();
		Integer to=request.getToPhone();
		Double transferAmount=request.getAmount();
		
		Account fromAccount=accountRepo.getAccountDetailsByMobile(from);
	*/	
		Long cardno=request.getFromCardNo();
		Integer cvv=request.getCvv();
		Date exp=request.getExpiryDate();
		Double transferAmount=	request.getTransferAmount();
		Account fromAccount=	accountRepo.getAccountDetails(cardno, cvv, exp);
		if(Objects.isNull(fromAccount)){
			throw new TransferException("From Mobile Number  is Invalid");
		}
		Account toAccount=accountRepo.findByAccontno(request.getToAccontNo());
		if(Objects.isNull(toAccount)){
			throw new TransferException("TO Mobile Number is Invalid");
		}
		if(fromAccount.getAccontno().equals(toAccount.getAccontno())){
			throw new TransferException("Both From and To Mobile Numbers  are same");
		}
		
		if(fromAccount.getBalance()<=transferAmount) {
			throw new TransferException("Insufficient Balance in Account ..");
		}
		
		fromAccount.setBalance(fromAccount.getBalance()-transferAmount);
		toAccount.setBalance(toAccount.getBalance()+transferAmount);
	
		accountRepo.save(fromAccount);
		accountRepo.save(toAccount);
		
		Transaction fromTransaction=new Transaction(transferAmount,new Timestamp(System.currentTimeMillis()),fromAccount.getAccontno());
		fromTransaction.setMessage(transferAmount+" has been Debited from "+fromAccount.getAccontno());
		Transaction fromTrans=transactionRepo.save(fromTransaction);
		
		if(Objects.isNull(fromTrans)) {
			throw new TransferException("Transaction has been failed ");
		}
		Transaction toTransaction=new Transaction(transferAmount,new Timestamp(System.currentTimeMillis()),toAccount.getAccontno());
		toTransaction.setMessage(transferAmount+" has been Credited to "+toAccount.getAccontno());
		Transaction toTrans=transactionRepo.save(toTransaction);
		
		if(Objects.isNull(toTrans)) {
			throw new TransferException("Transaction has been failed ");
		}
		transationList.add(fromTrans);
		transationList.add(toTrans);
		
		return transationList;

	}

	@Override
	public Account getAccountByNo(Long accountNo) throws CustomerNotFoundException {
		Account acc=accountRepo.findByAccontno(accountNo);
		if(Objects.isNull(acc) && Objects.isNull(acc.getAid())) {
			throw new CustomerNotFoundException("Account not found");
		}
		return acc;
	}


}
