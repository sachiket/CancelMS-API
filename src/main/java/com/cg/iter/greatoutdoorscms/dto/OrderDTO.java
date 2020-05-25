package com.cg.iter.greatoutdoorscms.dto;

import java.util.Date;




public class OrderDTO {

	
	private String orderId;
	private String userId;
	private String addressId;
	private byte orderDispatchStatus;
	private Date orderInitiateTime;
	private Date orderDispatchTime;
	
	public OrderDTO() {}

	public OrderDTO(String orderId, String userId, String addressId, byte orderDispatchStatus, Date orderInitiateTime,
			Date orderDispatchTime) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.addressId = addressId;
		this.orderDispatchStatus = orderDispatchStatus;
		this.orderInitiateTime = orderInitiateTime;
		this.orderDispatchTime = orderDispatchTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public byte getOrderDispatchStatus() {
		return orderDispatchStatus;
	}

	public void setOrderDispatchStatus(byte orderDispatchStatus) {
		this.orderDispatchStatus = orderDispatchStatus;
	}

	public Date getOrderInitiateTime() {
		return orderInitiateTime;
	}

	public void setOrderInitiateTime(Date orderInitiateTime) {
		this.orderInitiateTime = orderInitiateTime;
	}

	public Date getOrderDispatchTime() {
		return orderDispatchTime;
	}

	public void setOrderDispatchTime(Date orderDispatchTime) {
		this.orderDispatchTime = orderDispatchTime;
	}
	
	
	
	
}
