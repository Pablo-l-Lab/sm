package com.imatia.statemachine.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imatia.statemachine.dao.OrderTrackingDao;
import com.imatia.statemachine.dao.OrderTrackingDaoImpl;
import com.imatia.statemachine.model.OrderTracking;
import com.imatia.statemachine.model.OrderTrackingFile;
import com.imatia.statemachine.model.OrderTrackingStatus;

@Service
public class OrderTrackingService {
	private int initStatus = OrderTrackingStatus.RECOGIDO_EN_ALMACÉN.ordinal();
	private int finalStatus = OrderTrackingStatus.ENTREGADO.ordinal();

	@Autowired
	public static OrderTrackingDao<OrderTracking> orderTrackingDao = new OrderTrackingDaoImpl();

	public OrderTrackingService() {
	}

	public List<String> processOrders(Collection<OrderTracking> orders) {
		List<String> returnList = new ArrayList<String>();
		final String SAVED = "SAVED";
		final String NOT_SAVED = "NOT_SAVED";

		for (OrderTracking thisOrder : orders) {

			if (isValidOrder(thisOrder)) {
				OrderTracking existingOrder = this.searchExistingOrder(thisOrder);

				if (existingOrder == null) {
					this.saveOrder(thisOrder);
					returnList.add(SAVED);
					System.out.println("SAVED ORDER: \n\n" + thisOrder + "\n\n");

				} else if (existingOrder != null) {
					System.out.println("VALIDATING EXISTING ORDER: " + thisOrder.getOrderId()
							+ " --------------------------------------------------------------------------------\n\n");

					if (this.isValidTransition(thisOrder, existingOrder)) {
						saveOrder(thisOrder);
						returnList.add(SAVED);
						System.out.println(
								"\t\tVALIDATION COMPLETED! => Updated order: " + thisOrder.getOrderId() + "\n\n\n\n");

					} else {
						returnList.add(NOT_SAVED);
						System.out.println("\t\tVALIDATION COMPLETED -X- Incorrect new state, cannot save order: "
								+ thisOrder.getOrderId() + "\n\n\t\t\t Can't make transition from status: "
								+ OrderTrackingStatus.valueOf(existingOrder.getTrackingStatusId())
								+ ", to status: "
								+ OrderTrackingStatus.valueOf(thisOrder.getTrackingStatusId()) + "\n\n");
					}
				}
			} else {
				returnList.add(NOT_SAVED);
				System.out.println("\t\t UNABLE TO SAVE ORDER: " + thisOrder.getOrderId() + ", WITH TRACKING STATUS: "
						+ OrderTrackingStatus.valueOf(thisOrder.getTrackingStatusId()) + " - PLEASE INTRODUCE A VALID STATE AND RETRY \n\n");
			}

		}
		return returnList;

	}

	private boolean isValidOrder(OrderTracking thisOrder) {
		return (thisOrder.getOrderId() != null) && (thisOrder.getChangeStatusDate() != null)
				&& isValidStatusID(thisOrder.getTrackingStatusId());
	}

	private boolean isValidStatusID(int statusID) {
		return OrderTrackingStatus.valueOf(statusID) != OrderTrackingStatus.ESTADO_INCORRECTO;
	}

	public OrderTracking searchExistingOrder(OrderTracking existingOrder) {
		return orderTrackingDao.getOrderByID(existingOrder.getOrderId());
	}

	private boolean isValidTransition(OrderTracking thisOrder, OrderTracking existingOrder) {
		return (thisOrder.getTrackingStatusId() > initStatus) && (existingOrder.getTrackingStatusId() < finalStatus);
	}

	private void saveOrder(OrderTracking order) {
		order.setOrderTrackingStatus();
		orderTrackingDao.save(order);
	}

}
