package com.imatia.statemachine.dao;

import java.util.List;

import com.imatia.statemachine.model.OrderTracking;

public interface OrderTrackingDao <T> {
	    
		OrderTracking getOrderByID(Long orderId);
	    
	    List<T> getAll();
	    
	    void save(T t);
	    
	    void update(T t, String[] params);
	    
	    void delete(T t);


}
