package com.web.store.domain;

import java.util.Date;
import java.util.List;

public class Orders {

	//�q��id
	private String id;
	//�q��ɶ�
	private Date orderDate;
	//�q�檬�A 1���T�{ 0�H�T�{
	private int status = 1;
	//�q����B
	private double total;
	
//	private List<OrderLineItem> orderLineItems;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrdersDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
