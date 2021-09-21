package com.example.CarRentalApplication.service;

import java.util.List;

import com.example.CarRentalApplication.domain.Car;

public interface CarService {

	List<Car> getAllCars();
	
	Car findById(long id);
	
	Car addCar(Car car);
	
	void updateCar(Car modifiedCar);
	
	void deleteCarById(long id);
}
