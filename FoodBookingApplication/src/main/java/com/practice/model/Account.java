package com.practice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "account")
public class Account {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long aid;
	
	private Long accontno;
	
	private Double balance;
	
	private String ifsccode;
	
	private String name;
	
	private String type;
	
	private Integer cvv;
	
	private Long cardno;
	
	private Date expireDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	private Customer customer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public Long getAccontno() {
		return accontno;
	}

	public void setAccontno(Long accontno) {
		this.accontno = accontno;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Long getCardno() {
		return cardno;
	}

	public void setCardno(Long cardno) {
		this.cardno = cardno;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	
	
}
