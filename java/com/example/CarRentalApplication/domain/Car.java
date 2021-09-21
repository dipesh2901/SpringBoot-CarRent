package com.example.CarRentalApplication.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String name;

	@NotNull
	private String fuel;

	@NotNull
	private String engineCapacity;

	private String returnStatus;

	public Car(@NonNull String name, String fuel, String engineCapacity, String returnStatus) {
		super();
		this.name = name;
		this.fuel = fuel;
		this.engineCapacity = engineCapacity;
		this.returnStatus = returnStatus;

	}

}
