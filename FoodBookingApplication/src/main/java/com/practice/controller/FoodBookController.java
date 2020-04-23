package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.exception.CustomerNotFoundException;
import com.practice.exception.DBException;
import com.practice.exception.TransferException;
import com.practice.exception.UserNotFoundException;
import com.practice.model.Account;
import com.practice.model.HungerBoxUser;
import com.practice.model.Item;
import com.practice.model.Order;
import com.practice.model.OrderRequest;
import com.practice.model.UserRequest;
import com.practice.service.ItemService;
import com.practice.service.OrderService;
import com.practice.service.UserService;

@Controller
@RequestMapping("/foodbook/api")
public class FoodBookController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OrderService orderservice;
	
	@PostMapping("/registeruser")
	public ResponseEntity<HungerBoxUser> registerUser(@RequestBody UserRequest request) throws DBException {
		HungerBoxUser user=userService.registerUser(request);
		return  new ResponseEntity(user, HttpStatus.OK);
	}
	@GetMapping("/fetchbyvendorname")
	public ResponseEntity<List<Item>> fetchItemsByVendorName(@RequestParam String vendorName) throws DBException{
		List<Item> items=itemService.getItemsByVendorname(vendorName);
		return  new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/fetchByItemname")
	public ResponseEntity<List<Item>> fetchItemListByItemName(@RequestParam String itemname) throws DBException{
		List<Item> items=itemService.getItemsByItemname(itemname);
		return  new ResponseEntity(items, HttpStatus.OK);	
	}
	@PostMapping("/orderitems")
	public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) throws UserNotFoundException, DBException, TransferException{
		Order order= orderservice.placeOrder(orderRequest);
		return  new ResponseEntity(order, HttpStatus.OK);
	}
	
	@GetMapping("/fetchAccount")
	public ResponseEntity<Account> fetchAccount(@RequestParam Long acno) throws CustomerNotFoundException {
		Account aa= orderservice.getAccount(acno);
		return  new ResponseEntity(aa, HttpStatus.OK);
	}
	
	@GetMapping("/orderhistory")
	public ResponseEntity<List<Order>> orderHistory() throws DBException {
		List<Order> orders= orderservice.fetchOrderHistory();
		return  new ResponseEntity(orders, HttpStatus.OK);
	}
	@GetMapping("/info")
	public ResponseEntity<String> getPortNo() {
		String portNo=orderservice.getPortNo();
		return  new ResponseEntity(portNo, HttpStatus.OK);
	}
	
}
 