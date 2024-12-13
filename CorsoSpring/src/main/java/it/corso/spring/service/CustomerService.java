package it.corso.spring.service;

import it.corso.spring.dao.CustomerDao;
import it.corso.spring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

@Service("customerService")
public class CustomerService {
	@Autowired
	CustomerDao customerDao;

	public List<Customer> getAllCustomers() {
 	return customerDao.getAllCustomers();
	}

	public Customer getCustomer(int id) {
		return null;
	}

	public Customer addCustomer(Customer customer) {
		return customerDao.addCustomer(customer);
	}

	public Customer updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}


	public void deleteCustomer(int id) {
		customerDao.deleteCustomer(id);
	}

}
