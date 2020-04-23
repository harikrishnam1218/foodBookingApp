package com.practice.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.model.Item;
@Repository
public interface ItemRepository  extends JpaRepository<Item, Long>{
	
	List<Item> getByItemname(String itemname);
	
	@Query(" select item from Item item ,Vendor v where item.vid=v.vid and v.vendorname=?1")
	List<Item> fetchItemsByVendorName(String vendorname);

	//@Query(" select item from Item item ,Vendor v where item.vid=v.vid and v.vendorname=?1")
	List<Item> getByVid(Long vid);

}

