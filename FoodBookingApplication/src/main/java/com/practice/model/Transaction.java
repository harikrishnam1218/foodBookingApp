package com.practice.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	  	@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long tid;

	    private Double transactionAmount;

	    private Timestamp transactiondate;
	    
	    private Long accountnumber;
	    
	    private String message;

		public Long getTid() {
			return tid;
		}

		public void setTid(Long tid) {
			this.tid = tid;
		}

		public Double getTransactionAmount() {
			return transactionAmount;
		}

		public void setTransactionAmount(Double transactionAmount) {
			this.transactionAmount = transactionAmount;
		}

		public Timestamp getTransactiondate() {
			return transactiondate;
		}

		public void setTransactiondate(Timestamp transactiondate) {
			this.transactiondate = transactiondate;
		}

		public Long getAccountnumber() {
			return accountnumber;
		}

		public void setAccountnumber(Long accountnumber) {
			this.accountnumber = accountnumber;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Transaction(Double transactionAmount, Timestamp transactiondate, Long accountnumber) {
			super();
			this.transactionAmount = transactionAmount;
			this.transactiondate = transactiondate;
			this.accountnumber = accountnumber;
		}

		public Transaction() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}
