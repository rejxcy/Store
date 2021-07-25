package com.web.store.dao;

import java.util.List;

import com.web.store.domain.OrderLineItem;

public interface OrderLineItemDao {
	
	OrderLineItem findByPk(long pk);

	List<OrderLineItem> findAll();

	void create(OrderLineItem lineItem);

	void modify(OrderLineItem lineItem);

	void remove(int pk);

}
