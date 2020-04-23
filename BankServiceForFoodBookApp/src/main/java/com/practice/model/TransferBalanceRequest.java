package com.practice.model;

import java.util.Date;

public class TransferBalanceRequest {
	private Long fromCardNo;
	
	private Integer cvv;
	
	private Date expiryDate;
	
	private Long toAccontNo;
	
	private Double transferAmount;

	public Long getFromCardNo() {
		return fromCardNo;
	}

	public void setFromCardNo(Long fromCardNo) {
		this.fromCardNo = fromCardNo;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getToAccontNo() {
		return toAccontNo;
	}

	public void setToAccontNo(Long toAccontNo) {
		this.toAccontNo = toAccontNo;
	}

	public Double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}

	
}
