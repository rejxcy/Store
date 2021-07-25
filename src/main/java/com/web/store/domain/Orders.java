package com.web.store.domain;

import java.util.Date;
import java.util.List;

public class Orders {

	//訂單id
	private String id;
	//訂單時間
	private Date orderDate;
	//訂單狀態 1未確認 0以確認
	private int status = 1;
	//訂單金額
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
