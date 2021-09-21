package com.example.CarRentalApplication.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRentalApplication.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	Customer findByEmail(String email);
	Customer findByPassword(String password);
}
