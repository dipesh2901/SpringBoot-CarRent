package com.example.CarRentalApplication.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentedCar {

	@Id
	private long carId;
	
	private String carName;
	private String customerId;
	private String customerName;
	@NonNull
	private Date issueddate;
	public RentedCar(String carName, String customerId, String customerName) {
		super();
		this.carName = carName;
		this.customerId = customerId;
		this.customerName = customerName;
	}
	
	
}
