package com.LTIMindtree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.LTIMindtree.entity.Customer;
import com.LTIMindtree.exceptionhandler.APIRequestTimeoutException;
import com.LTIMindtree.response.CustomerResponse;
import com.LTIMindtree.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerService customerService;


	
   @GetMapping("/test")
   @HystrixCommand(fallbackMethod = "getFallbackResponse" , commandProperties = {@HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds", value = "1000" )})
	public String test() throws InterruptedException {
	   Thread.sleep(3000);
	   
	return customerService.test();
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerResponse>> getCustomer() {

		List<CustomerResponse> customer = customerService.getCustomer();
		
		ResponseEntity<List<CustomerResponse>> customerResponse = ResponseEntity.status(HttpStatus.OK).body(customer);

		return customerResponse;

	}

	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerResponse> getCustomeById(@PathVariable("customerId") int id) {
		CustomerResponse customerById = customerService.getCustomeById(id);
		ResponseEntity<CustomerResponse> customerResponse = ResponseEntity.status(HttpStatus.OK).body(customerById);
		return customerResponse;

	}

	
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<List<CustomerResponse>> deleteCustomeById(@PathVariable("customerId") int id) {

		List<CustomerResponse> deleteCustomeById = customerService.deleteCustomeById(id);

		ResponseEntity<List<CustomerResponse>> customerResponse = ResponseEntity.status(HttpStatus.OK).body(deleteCustomeById);

		return customerResponse;

	}


	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomeById(@RequestBody Customer customer) {
		customer.setStatus("true");
		 Customer updateCustomeById = customerService.updateCustomeById(customer);
		 ResponseEntity<Customer> customerResponse = ResponseEntity.status(HttpStatus.OK).body(updateCustomeById);
		 return customerResponse;
	}


	@PostMapping("/customer")
	public ResponseEntity<List<CustomerResponse>> saveCustomer(@RequestBody Customer customer) {
		
		 customer.setStatus("true");
		 List<CustomerResponse> saveCustomer = customerService.saveCustomer(customer);
		 ResponseEntity<List<CustomerResponse>> customerResponse = ResponseEntity.status(HttpStatus.OK).body(saveCustomer);
		 return customerResponse;

	}
	

	public String  getFallbackResponse() {
		
		throw new APIRequestTimeoutException("Request took too much time to respond..Plese Try after sometime");
		
		
	}

}
