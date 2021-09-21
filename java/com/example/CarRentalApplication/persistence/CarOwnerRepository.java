package com.example.CarRentalApplication.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CarRentalApplication.domain.CarOwner;

public interface CarOwnerRepository extends JpaRepository<CarOwner,Long> {

	CarOwner findByEmail(String email);
	CarOwner findByPassword(String password);
}
