package com.practice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendor")
public class Vendor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vid;
	
	private String vendorname;
	
	private String vendordesc;

	public Long getVid() {
		return vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String getVendordesc() {
		return vendordesc;
	}

	public void setVendordesc(String vendordesc) {
		this.vendordesc = vendordesc;
	}
	

}
