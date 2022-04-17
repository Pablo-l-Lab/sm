package com.imatia.statemachine.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.statemachine.model.OrderTracking;
import com.imatia.statemachine.model.OrderTrackingFile;

@RestController
public class OrderTrackingController {

	List<OrderTracking> db = new ArrayList<OrderTracking>();

	@PostMapping(value = "/order/tracking", 
				consumes = { MediaType.APPLICATION_JSON_VALUE,
							 MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<HttpStatus> orderTracking(@RequestBody OrderTrackingFile request) {
		ResponseEntity<HttpStatus> response = null;
		boolean saved = false;
		for (OrderTracking newOrder : request.getOrderTrackings()) {
			OrderTracking oldOrder = searchOrder(newOrder);
			if (oldOrder == null) {
				saved = saveOrder(oldOrder);
				response = new ResponseEntity<>(HttpStatus.CREATED);
				System.out.println("saved order: " + saved);
			} else if (oldOrder != null){
				response = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
				if(oldOrder.getTrackingStatusId() >= 1 && oldOrder.getTrackingStatusId() <= 4) {
					processOrder(newOrder, oldOrder);
					System.out.println("saved order: Order already reported");
				} else {
					response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
					System.out.println("Estado incorrecto");
				}
				
			}
		}
		return response;

	}
	
	private void processOrder(OrderTracking newOrder, OrderTracking oldOrder) {
		int newStatus = newOrder.getTrackingStatusId();
		int oldStatus = oldOrder.getTrackingStatusId();
		OrderTracking orderToSave = new OrderTracking();
		// reglas de negocio
	}

	private Boolean saveOrder(OrderTracking order) {
		Boolean result = this.db.add(order) ? true : false;
		return result;
	}

	private OrderTracking searchOrder(OrderTracking searchedOrder) {
		OrderTracking order = null;
		Long searchedOrderID = searchedOrder.getOrderId();
		try {
			int i = 0;
			boolean orderNotFound = true;
			while (i < this.db.size() && orderNotFound) {
				if (this.db.get(i).getOrderId().equals(searchedOrderID)) {
					order = searchedOrder;
					orderNotFound = false;
				}
				i++;
			}
		} catch (Exception e) {
		}
		return order;
	}

}
