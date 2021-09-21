package com.example.CarRentalApplication.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.CarRentalApplication.domain.Car;
import com.example.CarRentalApplication.domain.RentedCar;
import com.example.CarRentalApplication.service.CarService;
import com.example.CarRentalApplication.service.RentedCarService;

@Controller
public class RentCarController {

	@Autowired
	private CarService carService;

	@Autowired
	private RentedCarService rentedCarService;

	@GetMapping("/rentCar")
	public String rentCarForm(Model model) {
		System.out.println(rentedCarService.getAllRentedCars());
		model.addAttribute("listCarRents", rentedCarService.getAllRentedCars());
		return "rentCar";
	}

	@GetMapping("/returnRentedCar/{id}")
	public String returnCarById(@PathVariable long id) {
		if (carService.findById(id) != null) {
			Car car = carService.findById(id);
			car.setReturnStatus("true");
			rentedCarService.returnRentedCarById(id);
			return "redirect:/rentCar";
		} else {
			return "redirect:/rentCar?doesntExist";
		}
	}

	@GetMapping("/carRent/new")
	public String getRentCarForm(Model model) {
		model.addAttribute("carRent", new RentedCar());
		return "rentCarForm";
	}

	@PostMapping("/carRents")
	public String addRentedCars(@ModelAttribute("carRent") RentedCar rentedCar) {
		try {
			carService.findById(rentedCar.getCarId());
			System.out.println(carService.findById(rentedCar.getCarId()));

			// System.out.println(rentedCarService.getRentedCarById(rentedCar.getCarId()));

		} catch (Exception e) {
			System.out.println("Exception Catched: " + e.getMessage());
			return "redirect:/carRent/new?failure";
		}

		Car car = carService.findById(rentedCar.getCarId());
		if (car.getReturnStatus() == null || car.getReturnStatus().equals("true")) {
			car.setReturnStatus("false");
			rentedCar.setIssueddate(new Date());
			System.out.println("Rented Car: " + rentedCar);
			rentedCarService.addRentedCar(rentedCar);
			return "redirect:/carRent/new?success";
		} else {
			return "redirect:/carRent/new?rented";
		}

		/*
		 * rentedCarService.getAllRentedCars().stream().map(rCar->{
		 * if(rCar.getCarId()==rentedCar.getCarId()) { System.out.println("reachingg");
		 * return "redirect:/carRent/new?rented"; } else {
		 * System.out.println("reachedd"); rentedCarService.addRentedCar(rentedCar);
		 * return "redirect:/carRent/new?success"; } });
		 */

		/*
		 * if(carService.findById(rentedCar.getCarId())!=null) {
		 * System.out.println(rentedCar.getCarId());
		 * System.out.println(carService.findById(rentedCar.getCarId()));
		 * //rentedCarService.addRentedCar(rentedCar); return
		 * "redirect:/rentCarForm?success"; } else { return
		 * "redirect:/rentCarForm?failure"; }
		 * 
		 */
	}
}
