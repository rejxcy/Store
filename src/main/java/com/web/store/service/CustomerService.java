package com.web.store.service;

import com.web.store.domain.Customer;

public interface CustomerService {
	
	/**
	 * �B�z�Ȥ�n��
	 * @param customer
	 * @return  ���\��true, ���Ѷ�false
	 */
	boolean login(Customer customer);
	
	
	/**
	 * �B�z�Ȥ���U
	 * @param customer
	 * @throws ServiceException  �B�z���� �ߥX�ҥ~
	 */
	
	void register(Customer customer) throws ServiceException;

}
