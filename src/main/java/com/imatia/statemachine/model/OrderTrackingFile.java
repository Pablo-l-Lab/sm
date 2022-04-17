package com.imatia.statemachine.model;

import java.util.Collection;
import java.util.Iterator;

public class OrderTrackingFile implements Iterable<OrderTracking> {

	private Collection<OrderTracking> orderTrackings;

	public OrderTrackingFile() {
	}

	public Collection<OrderTracking> getOrderTrackings() {
		return this.orderTrackings;

	}

	public void setOrderTracking(Collection<OrderTracking> orderTrackings) {
		this.orderTrackings = orderTrackings;
	}

	public OrderTracking getOrderByID(Long id) {
		int i = 0;
		OrderTracking searchedOrder = null;
		OrderTracking order = null;
		while (i < this.orderTrackings.size() && searchedOrder == null) {
			order = (OrderTracking) orderTrackings.toArray()[i];
			if (order.getOrderId() == id) {
				searchedOrder = order;
			}
		}
		return searchedOrder;
	}

	@Override
	public String toString() {
		return "OrderTrackings [" + orderTrackings + "]";
	}

	@Override
	public Iterator<OrderTracking> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
