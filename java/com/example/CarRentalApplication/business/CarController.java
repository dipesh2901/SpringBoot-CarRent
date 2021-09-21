package com.example.CarRentalApplication.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.CarRentalApplication.domain.Car;
import com.example.CarRentalApplication.domain.CarOwner;
import com.example.CarRentalApplication.service.CarService;

@Controller
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/car")
	public String getCarPage(Model model) {
		model.addAttribute("listCars", carService.getAllCars());
		return "car";
	}

	@GetMapping("/carForm/new")
	public String getCarForm(Model model) {
		model.addAttribute("car", new Car());
		return "carForm";
	}

	@PostMapping("/cars")
	public String addCar(@ModelAttribute("car") Car car) {
		System.out.println("Entetered");
		/*
		 * Car car2=carService.findById(car.getId()); car2.setReturnStatus("");
		 */
		carService.addCar(car);
		return "redirect:/carForm/new?success";
	}

	@GetMapping("/showFormForCarUpdate/{id}")
	public String getCarOwnerUpdateForm(@PathVariable long id, Model model) {
		model.addAttribute("car", carService.findById(id));
		return "carUpdateForm";
	}

	@PostMapping("/saveCar")
	public String updateCar(@ModelAttribute("car") Car car, Model model) {
		System.out.println(car.getId());
		carService.findById(car.getId()).setName(car.getName());
		carService.findById(car.getId()).setFuel(car.getFuel());
		carService.findById(car.getId()).setEngineCapacity(car.getEngineCapacity());

		model.addAttribute("listCars", carService.getAllCars());
		return "car";
	}
	
	@GetMapping("/deleteCar/{id}")
	public String deleteCarById(@PathVariable(value = "id") long id) {
		Car car=carService.findById(id);
		if(car.getReturnStatus()==null||car.getReturnStatus().equals("true")) {
			carService.deleteCarById(id);
			return "redirect:/car";
		}
		else {
			return "redirect:/car?notReturned";
			
		}
	}

}
