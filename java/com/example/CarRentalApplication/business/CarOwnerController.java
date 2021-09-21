package com.example.CarRentalApplication.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CarRentalApplication.domain.Admin;
import com.example.CarRentalApplication.domain.CarOwner;
import com.example.CarRentalApplication.dto.CarOwnerRegistrationDto;
import com.example.CarRentalApplication.persistence.CarOwnerRepository;
import com.example.CarRentalApplication.service.CarOwnerService;
import com.example.CarRentalApplication.serviceImpl.CarOwnerServiceImpl;

@Controller
public class CarOwnerController {

	@Autowired
	CarOwnerRepository carOwnerRepository;

	@Autowired
	CarOwnerService carOwnerService;

	@GetMapping("/carOwner/new")
	public String createAdminForm(Model model) {
		CarOwner carOwner = new CarOwner();
		model.addAttribute("carOwner", carOwner);
		return "carOwner_form";
	}

	@PostMapping("/carOwnerLogin")
	public String carLogin(@ModelAttribute("carOwner") CarOwner carOwner, Model model) {
		System.out.println(carOwner);
		model.addAttribute("listCarOwners", carOwnerService.showAllCarOwner());
		if (carOwnerService.getCarOwnerByEmail(carOwner.getEmail()) != null&&carOwnerService.getCarOwnerByPassword(carOwner.getPassword())!=null) {
			return "carOwnerPersonalModule";
		} else
			return "redirect:/carOwner?error";
	}

	@GetMapping("/carOwnerLogin")
	public String carOwner(Model model) {
		System.out.println("here");
		model.addAttribute("listCarOwners", carOwnerService.showAllCarOwner());
		return "carOwnerPersonalModule";
	}

	@GetMapping("/carOwner")
	public String carOwnerForm(Model model) {
		return "carOwner";
	}

	@GetMapping("/carOwner/CarOwnerModule")
	public String getAllCarOwners(Model model) {
		model.addAttribute("listCarOwners", carOwnerService.showAllCarOwner());
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/carOwner/all")
	public List<CarOwner> getAllCarOwners() {
		return carOwnerRepository.findAll();
	}

	@GetMapping("/carOwner/{id}")
	public Optional<CarOwner> getCarOwnerById(@PathVariable Long id) {
		return carOwnerService.showCarOwnerById(id);
	}

	@GetMapping("/showFormForCarOwnerUpdate/{id}")
	public String getCarOwnerUpdateForm(@PathVariable long id, Model model) {
		model.addAttribute("carOwner", carOwnerService.showCarOwnerById(id));
		return "carOwnerUpdateForm";
	}

	@PostMapping("/carOwners")
	public String addNewCarOwner(@ModelAttribute("carOwner") CarOwner carOwner) {
		carOwnerService.saveCarOwner(carOwner);
		return "redirect:/carOwner/new?success";
	}

	@PostMapping("/saveCarOwner")
	public String updateCarOwner(@ModelAttribute("carOwner") CarOwner carOwner, Model model) {
		System.out.println(carOwner.getId());
		carOwnerService.deleteCarOwner(carOwner.getId());
		// System.out.println(carOwner.getId());
		carOwnerService.saveCarOwner(carOwner);
		model.addAttribute("listCarOwners", carOwnerService.showAllCarOwner());
		return "carOwnerModule";
	}

	@PutMapping("/update/carOwner")
	public CarOwner updateCarOwner(@RequestBody CarOwner carOwner) {
		Optional<CarOwner> optionalRecord = carOwnerRepository.findById(carOwner.getId());
		CarOwner existingCarOwner = optionalRecord.get();
		existingCarOwner.setName(carOwner.getName());
		existingCarOwner.setEmail(carOwner.getEmail());
		existingCarOwner.setMobile(carOwner.getMobile());
		existingCarOwner.setPassword(carOwner.getPassword());
		return carOwnerService.saveCarOwner(existingCarOwner);
	}

	@GetMapping("/deleteCarOwner/{id}")
	public String deleteCarOwner(@PathVariable(value = "id") long id, Model model) {
		System.out.println("Id: " + id);
		this.carOwnerService.deleteCarOwner(id);
		model.addAttribute("listCarOwners", carOwnerService.showAllCarOwner());
		return "carOwnerModule";
	}

	@GetMapping("/pageC/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<CarOwner> page = carOwnerService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<CarOwner> listCarOwners = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listCarOwners", listCarOwners);
		return "carOwnerModule";
	}

}
