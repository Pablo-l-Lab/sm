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
