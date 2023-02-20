package com.LTIMindtree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//@EnableRetry
@EnableHystrix
@EnableHystrixDashboard
public class CustomerServiceApplication {
 
	
	public static void main(String[] args) {
		
		
		SpringApplication.run(CustomerServiceApplication.class, args);
		System.out.println("hello hero");

		
	}

}
