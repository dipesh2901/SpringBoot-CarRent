package com.example.CarRentalApplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.CarRentalApplication.domain.CarOwner;
import com.example.CarRentalApplication.domain.Customer;
import com.example.CarRentalApplication.dto.CustomerRegistrationDto;

public interface CustomerService{

	Customer saveCustomer(Customer customer);
	
	List<Customer> getAllCustomers();
	
	Customer getCustomerByEmail(String email);
	
	Customer getCustomerByPassword(String password);
	
	Customer getCustomerById(long id);
	
	void deleteCustomerById(long id);
	
	Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
