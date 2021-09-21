package com.example.CarRentalApplication.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRentalApplication.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByEmail(String email);
}
