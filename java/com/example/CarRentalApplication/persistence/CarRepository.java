package com.example.CarRentalApplication.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CarRentalApplication.domain.Car;

public interface CarRepository extends JpaRepository<Car,Long> {

}
