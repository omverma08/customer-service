
package com.LTIMindtree.serviceimp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LTIMindtree.entity.Customer;
import com.LTIMindtree.exceptionhandler.APIRequestTimeoutException;
import com.LTIMindtree.exceptionhandler.CustomCustomerdException;
import com.LTIMindtree.exceptionhandler.DataBaseExpection;
import com.LTIMindtree.repository.CustomerRepository;
import com.LTIMindtree.response.CustomerResponse;
import com.LTIMindtree.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImp implements CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	
 //@Retryable(value=RuntimeException.class ,maxAttempts = 3 ,backoff=@Backoff(delay=10000))
 public String test()
 {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return "Welcome Hystrix";
	
	
 }
 
 //@Retryable(value=RuntimeException.class ,maxAttempts = 3 ,backoff=@Backoff(delay=100))
@HystrixCommand(fallbackMethod = "getFallbackResponse" , commandProperties = {@HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds", value = "1000" )})
 
public List<CustomerResponse> getCustomer() {
	 
		List<CustomerResponse> activeCustomer = null;
		
		List<Customer> customers = null;
		
		try {
			Thread.sleep(10000);
			customers = customerRepository.findAll();

			if (!customers.isEmpty()) {
				try {
					activeCustomer = customers.stream()
							.map(customer -> modelMapper.map(customer, CustomerResponse.class))
							.filter(customer -> customer.getStatus().equalsIgnoreCase("true"))
							.collect(Collectors.toList());
				}

				catch (Exception ex) {
					logger.error(ex.getMessage());
					throw new CustomCustomerdException("Internal Server Error");

				}
			} else
				throw new CustomCustomerdException("No data found");
		}

		catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataBaseExpection("Database Down for Now");
		}

		return activeCustomer;

	}

	public CustomerResponse getCustomeById(int id) {
		CustomerResponse customerResponse = null;

		try {
			Optional<Customer> customer = customerRepository.findById(id);

			if (customer.isPresent()) {
				customerResponse = modelMapper.map(customer, CustomerResponse.class);
				return customerResponse;
			} else
				throw new CustomCustomerdException("Customer Not found With Id:" + id);

		}

		catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataBaseExpection("Database Down for Now");
		}

	}

	@Transactional
	public List<CustomerResponse> deleteCustomeById(int id) {

		System.out.println("customer id:" + id);
		List<CustomerResponse> customers = null;
		int disableCustomerById;
		try {

			Optional<Customer> customerById = customerRepository.findById(id);

			if (customerById.isPresent()) {
				disableCustomerById = customerRepository.disableCustomerById(id);
				customers = getCustomer();
			} else
				throw new CustomCustomerdException("Customer Not found With Id:" + id);

		}

		catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new DataBaseExpection("Database Down for Now");
		}
		return customers;

	}

	public Customer updateCustomeById(Customer customer) {

		// get customer by id
		Customer updatedCustomer = null;

		try {
			int customereId = customer.getId();
			Optional<Customer> cust = customerRepository.findById(customereId);

			if (cust.isPresent()) {
				Customer customer2 = cust.get();
				customer2.setName(customer.getName());
				customer2.setGender(customer.getGender());
				customer2.setHobbies(customer.getHobbies());
				customer2.setQualification(customer.getQualification());
				updatedCustomer = customerRepository.save(customer2);
				return updatedCustomer;
			} 
			else
				throw new CustomCustomerdException("Customer Not found With Id:" + customereId);

		}

		catch (NullPointerException ex) {
			logger.error(ex.getMessage());
			throw new DataBaseExpection("Database Down for Now");

		}

	}

	public List<CustomerResponse> saveCustomer(Customer customer) {
		
		List<CustomerResponse> allcustomers;
		Customer insertCustomer = null;
		try {

			insertCustomer = customerRepository.save(customer);
			allcustomers = getCustomer();
		}

		catch (Exception ex) {

			logger.error(ex.getMessage());
			throw new DataBaseExpection("Database Down for Now");
		}

		return allcustomers;
	}
	
	

	
	public String  getFallbackResponse() {
		
		throw new APIRequestTimeoutException("Request took too much time to respond..Plese Try after sometime");
		
		
	}

	
	
}
