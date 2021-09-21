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

import com.example.CarRentalApplication.domain.CarOwner;
import com.example.CarRentalApplication.domain.Customer;
import com.example.CarRentalApplication.dto.CustomerRegistrationDto;
import com.example.CarRentalApplication.service.CarOwnerService;
import com.example.CarRentalApplication.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CarOwnerService carOwnerService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer")
	public String getAllCustomers(Model model) {
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "customer";
	}

	@GetMapping("/customerForm/new")
	public String getCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customerForm";
	}

	@PostMapping("/customers")
	public String provideCustomers(@ModelAttribute("customer") Customer customer) {
		System.out.println(customer);
		customerService.saveCustomer(customer);
		return "redirect:/customerForm/new?success";
	}

	@GetMapping("/customerLogin")
	public String getCustomerModule(Model model) {
//		/model.addAttribute("listCustomers",customerService.getAllCustomers());
		return "customerLogin";
	}

	@PostMapping("/customerLogin")
	public String verifyCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		if (customerService.getCustomerByEmail(customer.getEmail()) != null
				&& customerService.getCustomerByPassword(customer.getPassword()) != null) {
			return findPaginated(1, "firstName", "asc", model);
		} else {
			return "redirect:/customerLogin?error";
		}
	}

	@GetMapping("/customerModule")
	public String getCustomerModuleAgain(Model model) {
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/showFormForCustomerUpdate/{id}")
	public String getCustomerUpdateForm(@PathVariable long id, Model model) {
		System.out.println("Update: " + id + " ");
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customerUpdate";
	}

	@PostMapping("/saveCustomer")
	public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		System.out.println(customer);
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		customerService.getCustomerById(customer.getId()).setFirstName(customer.getFirstName());
		customerService.getCustomerById(customer.getId()).setLastName(customer.getLastName());
		customerService.getCustomerById(customer.getId()).setEmail(customer.getEmail());
		customerService.getCustomerById(customer.getId()).setMobile(customer.getMobile());
		customerService.getCustomerById(customer.getId()).setPassword(customer.getPassword());
		return "customer";
	}

	@GetMapping("/carShowroom")
	public String getCarShowroom() {
		return "carShowroom";
	}

	@GetMapping("/carOwnerDetails")
	public String carOwnerDetails(Model model) {
		model.addAttribute("listCarOwners", carOwnerService.showAllCarOwner());
		return "carOwnerDetailsForCustomers";
	}

	@GetMapping("/pageCus/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Customer> page = customerService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Customer> listCustomers = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listCustomers", listCustomers);
		return "customerModule";
	}

	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable(value = "id") long id, Model model) {
		System.out.println("Id: " + id);
		this.customerService.deleteCustomerById(id);
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "customer";
	}

}
