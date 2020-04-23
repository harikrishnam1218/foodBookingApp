package com.practice.model;

import java.util.Date;
import java.util.List;

public class OrderRequest {

	private List<ItemRequest> items;
	
	private String ordername;
	
	//private String orderno;
	
	private Long userid;
	
	private Long fromCardNo;
	
	private Integer cvv;
	
	private Date expiryDate;
	
	private Long toAccontNo;
	
	//private Double transferAmount;
	
	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
/*
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
*/
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public List<ItemRequest> getItems() {
		return items;
	}

	public void setItems(List<ItemRequest> items) {
		this.items = items;
	}

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

/*	public Double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}
	*/
}
