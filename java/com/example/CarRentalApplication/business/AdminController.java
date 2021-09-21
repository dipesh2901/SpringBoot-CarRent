package com.example.CarRentalApplication.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CarRentalApplication.domain.Admin;
import com.example.CarRentalApplication.dto.AdminRegistrationDto;
import com.example.CarRentalApplication.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/admin/new")
	public String createAdminForm(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "admin_form";
	}

	@PostMapping("/admins")
	public String addNewAdmin(@ModelAttribute("admins") AdminRegistrationDto adminRegistrationDto) {
		adminService.save(adminRegistrationDto);
		return "redirect:/";
	}

	@GetMapping("/registration")
	public String register() {
		System.out.println("Inside-R");
		return "registration";
	}

	@ModelAttribute("admin")
	public AdminRegistrationDto adminRegistrationDto() {
		System.out.println("Inside-ARD");
		return new AdminRegistrationDto();
	}

	@PostMapping("/adminRegistration")
	public String addAdmin(@ModelAttribute("admin") AdminRegistrationDto adminRegistrationDto) {
		System.out.println("Inside");
		adminService.save(adminRegistrationDto);
		return "redirect:/registration?success";
	}

	@PostMapping("/saveAdmin")
	public String updatedmin(@ModelAttribute("adminData") AdminRegistrationDto adminRegistrationDto) {
		adminService.save(adminRegistrationDto);
		return "redirect:/";
	}

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Admin> page = adminService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Admin> listAdmins = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listAdmins", listAdmins);
		return "index";
	}

	@GetMapping("/showFormForAdminUpdate/{id}")
	public String showFormForAdminUpdate(@PathVariable(value = "id") long id, Model model) {

		System.out.println("Admin Id: " + id);
		// get employee from the service
		Admin admin = adminService.getAdminById(id);
		System.out.println("Admin:" + admin);
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("admin", admin);
		return "update_admin";
	}
}
