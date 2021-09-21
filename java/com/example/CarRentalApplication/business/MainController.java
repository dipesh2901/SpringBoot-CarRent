package com.example.CarRentalApplication.business;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/AboutUs")
	public String getAboutUs() {
		return "AboutUs";
	}

	@GetMapping("/ContactUs")
	public String getContactUs() {
		return "ContactUs";
	}

}
