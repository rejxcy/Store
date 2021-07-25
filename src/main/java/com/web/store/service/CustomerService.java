package com.web.store.service;

import com.web.store.domain.Customer;

public interface CustomerService {
	
	/**
	 * 處理客戶登陸
	 * @param customer
	 * @return  成功傳true, 失敗傳false
	 */
	boolean login(Customer customer);
	
	
	/**
	 * 處理客戶註冊
	 * @param customer
	 * @throws ServiceException  處理失敗 拋出例外
	 */
	
	void register(Customer customer) throws ServiceException;

}
