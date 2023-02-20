package com.LTIMindtree.service;

import java.util.List;

import com.LTIMindtree.entity.Customer;
import com.LTIMindtree.response.CustomerResponse;

public interface CustomerService {

	List<CustomerResponse> getCustomer();

	CustomerResponse getCustomeById(int id);

	List<CustomerResponse> deleteCustomeById(int id);

	Customer updateCustomeById(Customer customer);

	List<CustomerResponse> saveCustomer(Customer customer);

	
	 public String test();
	
	
}
