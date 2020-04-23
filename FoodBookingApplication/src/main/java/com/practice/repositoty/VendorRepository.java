package com.practice.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.model.Item;
import com.practice.model.Vendor;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	List<Vendor> findByVendorname(String vendorname);
	}
