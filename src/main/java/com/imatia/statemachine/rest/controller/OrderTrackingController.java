package com.imatia.statemachine.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.statemachine.dao.OrderTrackingDao;
import com.imatia.statemachine.model.OrderTracking;
import com.imatia.statemachine.model.OrderTrackingFile;
import com.imatia.statemachine.services.OrderTrackingService;

@RestController
public class OrderTrackingController {
	
	OrderTrackingService orderService = new OrderTrackingService();
	@Autowired
	public static OrderTrackingDao<OrderTracking> orderTrackingDao;

	@PostMapping(value = "/order/tracking", 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	
	public List<String> orderTracking(@RequestBody OrderTrackingFile request) {
		
		return orderService.processOrders(request.getOrderTrackings());

	}

}
