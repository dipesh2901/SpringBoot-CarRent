package com.example.CarRentalApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDto {

	private String firstName;
	private String lastName;
	private String email;
	private long mobile;
	private String password;
	
	
}
