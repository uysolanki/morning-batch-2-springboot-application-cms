package com.excelr.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.cms.entity.Customer;
import com.excelr.cms.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository custRepo;

	public void addCustomer(Customer customer) {
		custRepo.save(customer);
		
	}

	public List<Customer> showCustomerList() {
		return custRepo.findAll();
		
	}

}
