package com.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.client.FoodBookingFeignClient;
import com.practice.exception.CustomerNotFoundException;
import com.practice.exception.DBException;
import com.practice.exception.TransferException;
import com.practice.exception.UserNotFoundException;
import com.practice.model.Account;
import com.practice.model.HungerBoxUser;
import com.practice.model.Item;
import com.practice.model.ItemRequest;
import com.practice.model.Order;
import com.practice.model.OrderRequest;
import com.practice.model.Transaction;
import com.practice.model.TransferBalanceRequest;
import com.practice.repositoty.ItemRepository;
import com.practice.repositoty.OrderRepository;
import com.practice.repositoty.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	 private FoodBookingFeignClient feignClient;
	
	@Override
	@Transactional
	public Order placeOrder(OrderRequest request) 
			throws UserNotFoundException, DBException, TransferException {
		Order order = new Order();
		Long userId = request.getUserid();
		Double  tranferAmount = 0.0;
		Optional<HungerBoxUser> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException("HungerBox User Details Not Found");
		}
		List<ItemRequest> items = request.getItems();
		if (Objects.isNull(items) && items.isEmpty()) {
			throw new DBException("Item Deatils List is Empty");
		}
		//List<Item> itemList = ItemData(tranferAmount, items);
		List<Item> itemList= new ArrayList<Item>();
		for (ItemRequest itemRequest : items) {
			Integer quantity=itemRequest.getQuantity();
			Optional<Item> it=itemRepo.findById(itemRequest.getItemid());
			if(it.isPresent()) {
				Double itemCost=it.get().getCost();
				tranferAmount=tranferAmount+(itemCost*quantity);
				itemList.add(it.get());
			}
		}
		TransferBalanceRequest balRequest = orderData(request, order, user, itemList, tranferAmount);
		List<Transaction> transactionList = feignClient.transferBalance(balRequest);
		if (Objects.isNull(transactionList) && transactionList.isEmpty()) {
			throw new DBException("Transaction has been Failed for Amount Transfer");
		}
		Order orderRequest = orderRepo.save(order);

		if (Objects.isNull(orderRequest) && Objects.isNull(orderRequest.getOid())) {
			throw new DBException("Order has been failed");
		}
		/*	itemList.stream().forEach(i->{i.setOrder(orderRequest);
			itemRepo.save(i);});
*/	
		for (Item item : itemList) {
			item.setOrder(orderRequest);
			itemRepo.save(item);
		}
		return orderRequest;
	}


	private TransferBalanceRequest orderData(OrderRequest request, Order order, Optional<HungerBoxUser> user,
			List<Item> items,Double tranferAmount) {
		order.setOrdername(request.getOrdername());
		order.setOrderno(generateRandom());
		order.setItems(items);
		order.setUser(user.get());
		order.setOrderamount(tranferAmount);
		TransferBalanceRequest balRequest=new TransferBalanceRequest();
		balRequest.setToAccontNo(request.getToAccontNo());
		balRequest.setCvv(request.getCvv());
		balRequest.setFromCardNo(request.getFromCardNo());
		balRequest.setExpiryDate(request.getExpiryDate());
		balRequest.setTransferAmount(tranferAmount);
		return balRequest;
	}

	private  String generateRandom() {
		String aToZ="ABCDEFGHIJKLMNOPQRSUVWXYZ1234567890";
	    Random rand=new Random();
	    StringBuilder res=new StringBuilder();
	    for (int i = 0; i < 17; i++) {
	       int randIndex=rand.nextInt(aToZ.length()); 
	       res.append(aToZ.charAt(randIndex));            
	    }
	    return res.toString();
	}

	@Override
	public Account getAccount(Long accountNo) throws CustomerNotFoundException {
		 return feignClient.getAccountDetail(accountNo);
	}


	@Override
	public List<Order> fetchOrderHistory() throws DBException {
		List<Order> orderList=orderRepo.fetchOrderHistory();
		if(Objects.isNull(orderList) && orderList.isEmpty()) {
			throw new DBException("OrderList not Available");
		}
		return orderList;
	}


	@Override
	public String getPortNo() {
		return feignClient.getInfo();
	}
}
