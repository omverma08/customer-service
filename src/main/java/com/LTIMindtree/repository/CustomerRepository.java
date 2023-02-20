package com.LTIMindtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LTIMindtree.entity.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	
	@Modifying
	@Query( value = "update customer set status='false' where id=:id", nativeQuery = true)
	 int disableCustomerById(@Param("id") int id);

	
	

	
}
