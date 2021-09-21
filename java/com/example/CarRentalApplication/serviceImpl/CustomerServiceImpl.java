package com.example.CarRentalApplication.serviceImpl;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CarRentalApplication.domain.Admin;
import com.example.CarRentalApplication.domain.Customer;
import com.example.CarRentalApplication.domain.Role;
import com.example.CarRentalApplication.dto.CustomerRegistrationDto;
import com.example.CarRentalApplication.persistence.CustomerRepository;
import com.example.CarRentalApplication.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
		
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	
	

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public Customer getCustomerById(long id) {
		return customerRepository.getById(id);
	}

	@Override
	public void deleteCustomerById(long id) {
		customerRepository.deleteById(id);
		
	}

	@Override
	public Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.customerRepository.findAll(pageable);
	}

	@Override
	public Customer getCustomerByPassword(String password) {
		return customerRepository.findByPassword(password);
	}
	
	

}
