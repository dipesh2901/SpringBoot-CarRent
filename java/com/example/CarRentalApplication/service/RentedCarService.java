package com.example.CarRentalApplication.service;

import java.util.List;

import com.example.CarRentalApplication.domain.RentedCar;

public interface RentedCarService {

	List<RentedCar> getAllRentedCars();
	RentedCar getRentedCarById(long id);
	void addRentedCar(RentedCar rentedCar);
	void returnRentedCarById(long id);
}
