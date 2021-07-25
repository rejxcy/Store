package com.web.store.dao;

import java.util.List;

import com.web.store.domain.Customer;

public interface CustomerDao {
	
	Customer findByPk(String pk);
	
	void create(Customer customer);
	
	void modify(Customer customer);
	
	void remove(String pk);
	
	List<Customer> findAll();

}

