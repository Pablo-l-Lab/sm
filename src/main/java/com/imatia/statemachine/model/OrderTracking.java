package com.imatia.statemachine.model;

import java.util.Date;


public class OrderTracking {

	private Long orderId; // 230688716
	private int trackingStatusId; // 1
	private OrderTrackingStatus orderTrackingStatus;
	private Date changeStatusDate; // "2021-02-24T10:30:30.000+01:00"
	private Date lastNoticeDate;

	public OrderTracking() {
		setLastNoticeDate();
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getTrackingStatusId() {
		return trackingStatusId;
	}

	public void setTrackingStatusId() {
		setOrderTrackingStatus();
	}

	public OrderTrackingStatus getOrderTrackingStatus() {
		return orderTrackingStatus;
	}
	
	public void setOrderTrackingStatus() {
		this.orderTrackingStatus = OrderTrackingStatus.valueOf(this.trackingStatusId);
	}
	
	public Date getChangeStatusDate() {
		return changeStatusDate;
	}

	public void setChangeStatusDate(Date changeStatusDate) {
		this.changeStatusDate = changeStatusDate;
	}

	public Date getLastNoticeDate() {
		return lastNoticeDate;
	}
	
	public void setLastNoticeDate() {
		this.lastNoticeDate = new Date();
	}

	@Override
	public String toString() {
		return "OrderTracking:	[ "
					+ "\n			orderId 		= "			+ orderId
					+ "\n 			trackingStatusId 	= "		+ trackingStatusId
					+ "\n 			orderTrackingStatus	= "	+ orderTrackingStatus
					+ "\n 			changeStatusDate 	= "		+ changeStatusDate
					+ "\n 			lastNoticeDate 		= "		+ lastNoticeDate
					+ "\n		]";
	}
	


}
