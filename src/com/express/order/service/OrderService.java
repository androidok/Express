package com.express.order.service;

import java.util.List;

import com.express.model.Order;

public interface OrderService {

	public List<Order> rangeFindOrder(int first, Double receiveLongitude,Double receiveLatitude);
	
	public List<Order> findOrderById(int first,String Id);
	
	public void create(Order order);
	
	public boolean receive(Order order) throws NoSuchFieldException;
	
}
