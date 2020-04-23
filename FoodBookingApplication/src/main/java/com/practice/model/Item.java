package com.practice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long mid;

private String itemname;

private String itemdec;

private Double cost;

//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name="vid")

private Long vid;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="oid")
private Order order;

public Long getMid() {
	return mid;
}

public void setMid(Long mid) {
	this.mid = mid;
}

public String getItemname() {
	return itemname;
}

public void setItemname(String itemname) {
	this.itemname = itemname;
}

public String getItemdec() {
	return itemdec;
}

public void setItemdec(String itemdec) {
	this.itemdec = itemdec;
}

public Double getCost() {
	return cost;
}

public void setCost(Double cost) {
	this.cost = cost;
}

public Long getVid() {
	return vid;
}

public void setVid(Long vid) {
	this.vid = vid;
}

public Order getOrder() {
	return order;
}

public void setOrder(Order order) {
	this.order = order;
}

/*public Vendor getVendor() {
	return vendor;
}

public void setVendor(Vendor vendor) {
	this.vendor = vendor;
}

*/

	
}
