 package com.example.CarRentalApplication.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApplication.CarRentalApplication;
import com.example.CarRentalApplication.domain.Car;
import com.example.CarRentalApplication.persistence.CarRepository;
import com.example.CarRentalApplication.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	
	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}


	@Override
	public Car addCar(Car car) {
		return carRepository.save(car);
		
	}
	


	@Override
	public Car findById(long id) {
		return carRepository.getById(id);
	}


	@Override
	public void updateCar(Car modifiedCar) {
		carRepository.deleteById(modifiedCar.getId());
		carRepository.save(modifiedCar);
	}


	@Override
	public void deleteCarById(long id) {
		carRepository.deleteById(id);
		
	}

}
