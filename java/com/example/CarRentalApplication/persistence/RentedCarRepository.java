package com.example.CarRentalApplication.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CarRentalApplication.domain.RentedCar;

public interface RentedCarRepository extends JpaRepository<RentedCar,Long> {

}
