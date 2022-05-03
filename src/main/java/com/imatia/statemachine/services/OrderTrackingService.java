package com.imatia.statemachine.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imatia.statemachine.model.OrderTracking;
import com.imatia.statemachine.model.OrderTrackingStatus;
import com.imatia.statemachine.repository.OrderTrackingRepository;

@Service
public class OrderTrackingService {

	@Autowired
	private OrderTrackingRepository orderTrackingRepo;
	private int initialStatus = OrderTrackingStatus.RECOGIDO_EN_ALMACÉN.ordinal();
	private int finalStatus = OrderTrackingStatus.values().length;

	public OrderTrackingService() {
	}

	public List<String> processOrders(Iterable<OrderTracking> request) {
		final String SAVED = "Saved Order: ";
		final String INVALID_DATA = "Not saved. Invalid Status on Order: ";
		final String INVALID_TRANSITION = "Not saved. Invalid Transition on Order: ";
		List<String> response = new ArrayList<String>();

		for (OrderTracking thisOrder : request) {
			long thisOrderId = thisOrder.getOrderId();

			if (isValidEvent(thisOrder)) {
				OrderTracking existingOrder = searchExistingOrder(thisOrderId);
				if (existingOrder == null) {
					saveOrder(thisOrder);
					response.add(SAVED + thisOrderId);

				} else if (existingOrder != null) {

					if (isValidTransition(thisOrder, existingOrder)) {
						saveOrder(thisOrder);
						response.add(SAVED + thisOrderId);

					} else {
						response.add(INVALID_TRANSITION + thisOrderId);
					}
				}
			} else {
				response.add(INVALID_DATA + thisOrderId);
			}
		}
		return response;

	}

	private boolean isValidEvent(OrderTracking thisOrder) {
		return (thisOrder.getOrderId() != null) && (thisOrder.getChangeStatusDate() != null)
				&& isValidStatus(thisOrder.getTrackingStatusId());
	}

	private boolean isValidStatus(int statusID) {
		return OrderTrackingStatus.valueOf(statusID) != OrderTrackingStatus.ESTADO_INCORRECTO;
	}

	public OrderTracking searchExistingOrder(Long orderId) {
		return orderTrackingRepo.findFirst1ByOrderIdOrderByOrderTrackingIdDesc(orderId);
	}

	private boolean isValidTransition(OrderTracking thisOrder, OrderTracking existingOrder) {
		return (thisOrder.getTrackingStatusId() > initialStatus)
				&& (existingOrder.getTrackingStatusId() < (finalStatus - 1));
	}

	public void saveOrder(OrderTracking thisOrder) {
		thisOrder.setOrderTrackingStatus();
		try {
			OrderTracking savedOrder = orderTrackingRepo.save(thisOrder);
			System.out.println("\nSAVED ORDER: \n\n" + savedOrder + "\n\n");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
