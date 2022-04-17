package com.imatia.statemachine.model;

import java.util.Date;

public class OrderTracking {

	private Long orderId; // 230688716
	private Integer trackingStatusId; // 1
	private OrderTrackingStatus orderTrackingStatus;
	private Date changeStatusDate; // "2021-02-24T10:30:30.000+01:00"

	public OrderTracking() {
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getTrackingStatusId() {
		return trackingStatusId;
	}

	public void setTrackingStatusId(Integer trackingStatusId) {
		this.trackingStatusId = trackingStatusId;
	}

	public OrderTrackingStatus getOrderTrackingStatus() {
		return orderTrackingStatus;
	}

	public void setOrderTrackingStatus(OrderTrackingStatus orderTrackingStatus) {
		this.orderTrackingStatus = orderTrackingStatus;
	}
	
	public Date getChangeStatusDate() {
		return changeStatusDate;
	}

	public void setChangeStatusDate(Date changeStatusDate) {
		this.changeStatusDate = changeStatusDate;
	}

	@Override
	public String toString() {
		return "OrderTracking: {orderId=" + orderId + ", trackingStatusId=" + trackingStatusId + ", changeStatusDate="
				+ changeStatusDate + "}";
	}

}
