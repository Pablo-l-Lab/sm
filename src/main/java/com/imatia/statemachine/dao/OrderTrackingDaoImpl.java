package com.imatia.statemachine.dao;

import java.util.ArrayList;
import java.util.List;

import com.imatia.statemachine.model.OrderTracking;

public class OrderTrackingDaoImpl implements OrderTrackingDao<OrderTracking>{
	
	List<OrderTracking> db = new ArrayList<OrderTracking>();


	@Override
	public OrderTracking getOrderByID(Long orderId) {
		int i = 0;
		OrderTracking searchedOrder = null;
		while (i < this.db.size() && searchedOrder == null) {
			if (db.get(i).getOrderId().equals(orderId)) {
				searchedOrder = db.get(i);
			}
			i++;
		}
		return searchedOrder;
	}

	@Override
	public List<OrderTracking> getAll() {
		return db;
	}

	@Override
	public void save(OrderTracking order) {
		db.add(order);
	}

	@Override
	public void update(OrderTracking order, String[] params) {
	}

	@Override
	public void delete(OrderTracking order) {
		db.remove(order);
	}	
	
	
	
	
//	List<OrderTracking> db = new ArrayList<OrderTracking>();
//
//
//	@Override
//	public OrderTracking getOrderByID(Long orderId) {
//		int i = 0;
//		OrderTracking searchedOrder = null;
//		while (i < this.db.size() && searchedOrder == null) {
//			if (db.get(i).getOrderId().equals(orderId)) {
//				searchedOrder = db.get(i);
//			}
//			i++;
//		}
//		return searchedOrder;
//	}
//
//	@Override
//	public List<OrderTracking> getAll() {
//		return db;
//	}
//
//	@Override
//	public void save(OrderTracking order) {
//		db.add(order);
//	}
//
//	@Override
//	public void update(OrderTracking order, String[] params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(OrderTracking order) {
//		db.remove(order);
//	}

}
