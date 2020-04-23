package com.practice.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.exception.DBException;
import com.practice.model.Item;
import com.practice.repositoty.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Override
	public List<Item> getItemsByVendorname(String name) throws DBException {
		
		List<Item> items=itemRepo.fetchItemsByVendorName(name);
		if(Objects.isNull(items) && items.isEmpty()) {
			throw new DBException("Vendor Details Not Found");
		}
		return items;
	}

	@Override
	public List<Item> getItemsByItemname(String name) throws DBException {
		List<Item> items=itemRepo.getByItemname(name);
		if(Objects.isNull(items) && items.isEmpty()) {
			throw new DBException("Item Not Found");
		}
		return items;
	}

}
