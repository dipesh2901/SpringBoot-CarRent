package com.example.CarRentalApplication.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApplication.domain.RentedCar;
import com.example.CarRentalApplication.persistence.RentedCarRepository;
import com.example.CarRentalApplication.service.RentedCarService;

@Service
public class RentedCarServiceImpl implements RentedCarService {

	@Autowired
	private RentedCarRepository rentedCarRepository;
	
	@Override
	public List<RentedCar> getAllRentedCars() {
		return rentedCarRepository.findAll();
	}

	@Override
	public void returnRentedCarById(long id) {
		rentedCarRepository.deleteById(id);
	}

	@Override
	public void addRentedCar(RentedCar rentedCar) {
		rentedCarRepository.save(rentedCar);
		
	}

	@Override
	public RentedCar getRentedCarById(long id) {
		return rentedCarRepository.getById(id);
	}

}
