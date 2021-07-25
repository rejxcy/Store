package com.web.store.service.imp;

import com.web.store.dao.CustomerDao;
import com.web.store.dao.imp.CustomerDaoImpJdbc;
import com.web.store.domain.Customer;
import com.web.store.service.CustomerService;
import com.web.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService{
	
	CustomerDao customerDao = new CustomerDaoImpJdbc();

	@Override
	public boolean login(Customer customer) {
		
		Customer dbCustomerDao = customerDao.findByPk(customer.getId());
		if(dbCustomerDao.getPassword().equals(customer.getPassword())) {
			
			//�n�J���\
			customer.setName(dbCustomerDao.getName());
			customer.setPhone(dbCustomerDao.getPhone());
			customer.setAddress(dbCustomerDao.getAddress());
			customer.setBirthday(dbCustomerDao.getBirthday());

			return true;
		}
		return false;
	}

	@Override
	public void register(Customer customer) throws ServiceException {
		
		Customer dbCustomer = customerDao.findByPk(customer.getId());
		
		if(dbCustomer != null) { //�Τ�id�w�g�s�b
			throw new ServiceException("�Τ�"+customer.getId()+"�w�g�s�b!");
		}
		
		//�}�l���U
		customerDao.create(customer);
		
	}
	

}
