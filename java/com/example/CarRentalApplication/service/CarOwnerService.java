package com.example.CarRentalApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.CarRentalApplication.domain.Admin;
import com.example.CarRentalApplication.domain.CarOwner;
import com.example.CarRentalApplication.dto.CarOwnerRegistrationDto;

public interface CarOwnerService  {

	CarOwner saveCarOwner(CarOwner carOwner);
	
	List<CarOwner> showAllCarOwner();

	Optional<CarOwner> showCarOwnerById(Long id);
	
	CarOwner getCarOwnerByEmail(String email);
	
	CarOwner getCarOwnerByPassword(String password);

	void updateCarOwner(Long id,CarOwner carOwn);

	void deleteCarOwner(Long id);
	
	Page<CarOwner> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
