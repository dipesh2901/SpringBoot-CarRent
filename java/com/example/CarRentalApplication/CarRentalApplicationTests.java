package com.example.CarRentalApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.CarRentalApplication.domain.Admin;
import com.example.CarRentalApplication.domain.Car;
import com.example.CarRentalApplication.domain.CarOwner;
import com.example.CarRentalApplication.domain.Customer;
import com.example.CarRentalApplication.domain.Role;
import com.example.CarRentalApplication.persistence.AdminRepository;
import com.example.CarRentalApplication.persistence.CarOwnerRepository;
import com.example.CarRentalApplication.persistence.CarRepository;
import com.example.CarRentalApplication.persistence.CustomerRepository;
import com.example.CarRentalApplication.service.AdminService;
import com.example.CarRentalApplication.service.CarOwnerService;
import com.example.CarRentalApplication.service.CarService;
import com.example.CarRentalApplication.service.CustomerService;

@SpringBootTest
class CarRentalApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CarService carService;
	
	@Autowired
	private CarOwnerService carOwnerService;
	
	@Autowired
	private AdminService adminService;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private CarRepository carRepository;
	
	@MockBean
	private CarOwnerRepository carOwnerRepository;
	
	@MockBean
	private AdminRepository adminRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllCustomersTest() {
		when(customerRepository.findAll()).thenReturn(
				Stream.of(new Customer("Dipesh", "Dhirajlal", "dipesh@gmail.com", 8668259161l, "dipesh2901"))
						.collect(Collectors.toList()));
		assertEquals(1, customerService.getAllCustomers().size());
	}

	@Test
	public void getCustomerByEmailTest() {
		String email = "dipesh@gmail.com";
		when(customerRepository.findByEmail(email))
				.thenReturn(new Customer("Dipesh", "Dhirajlal", "dipesh@gmail.com", 8668259161l, "dipesh2901"));
		assertEquals(new Customer("Dipesh", "Dhirajlal", "dipesh@gmail.com", 8668259161l, "dipesh2901"),
				customerService.getCustomerByEmail(email));
	}

	@Test
	public void getCustomerByIdTest() {
		long id = 1l;
		when(customerRepository.getById(id))
				.thenReturn(new Customer("Dipesh", "Dhirajlal", "dipesh@gmail.com", 8668259161l, "dipesh2901"));
		assertEquals(new Customer("Dipesh", "Dhirajlal", "dipesh@gmail.com", 8668259161l, "dipesh2901"),
				customerService.getCustomerById(id));
	}

	@Test
	public void saveCustomerTest() {
		Customer customer = new Customer("Dipesh", "Dhirajlal", "dipesh@gmail.com", 8668259161l, "dipesh2901");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.saveCustomer(customer));
	}

	@Test
	public void deleteCustomerTest() {
		Customer customer = new Customer(1l, "Dipesh", "Dhirajlal", "dipesh@gmail.com", 8668259161l, "dipesh2901");
		customerService.deleteCustomerById(customer.getId());
		verify(customerRepository, timeout(1)).deleteById(customer.getId());
	}

	@Test
	public void getAllCarsTest() {
		when(carRepository.findAll())
				.thenReturn(Stream.of(new Car("Skoda Octavia", "Diesel", "1988cc", "")).collect(Collectors.toList()));
		assertEquals(1, carService.getAllCars().size());
	}

	@Test
	public void saveCarTest() {
		Car car = new Car("Skoda Octavia", "Diesel", "1988cc", "");
		when(carRepository.save(car)).thenReturn(car);
		assertEquals(car, carService.addCar(car));
	}

	@Test
	public void getCarByIdTest() {
		long id = 1l;
		when(carRepository.getById(id)).thenReturn(new Car("Skoda Octavia", "Diesel", "1988cc", ""));
		assertEquals(new Car("Skoda Octavia", "Diesel", "1988cc", ""), carService.findById(id));
	}

	@Test
	public void deleteCarTest() {
		Car car = new Car(1l, "Skoda Octavia", "Diesel", "1988cc", "");
		carService.deleteCarById(car.getId());
		verify(carRepository, timeout(1)).deleteById(car.getId());
	}
	
	
	@Test
	public void saveCarOwnerTest() {
		CarOwner carowner=new CarOwner("Dipesh","dipesh@gmail.com","dipesh2901",9898989898l);
		when(carOwnerRepository.save(carowner)).thenReturn(carowner);
		assertEquals(carowner, carOwnerService.saveCarOwner(carowner));
	}
	
	
	@Test
	public void getCarOwnerByEmailTest() {
		String email = "dipesh@gmail.com";
		when(carOwnerRepository.findByEmail(email))
				.thenReturn(new CarOwner("Dipesh","dipesh@gmail.com","dipesh2901",9898989898l));
		assertEquals(new CarOwner("Dipesh","dipesh@gmail.com","dipesh2901",9898989898l),
				carOwnerService.getCarOwnerByEmail(email));
	}
	
	@Test
	public void getAllCarOwnersTest() {
		when(carOwnerRepository.findAll()).thenReturn(
				Stream.of(new CarOwner("Dipesh","dipesh@gmail.com","dipesh2901",9898989898l))
						.collect(Collectors.toList()));
		assertEquals(1, carOwnerService.showAllCarOwner().size());
	}
	
	
	@Test
	public void deleteCarOwnerTest() {
		CarOwner carOwner=new CarOwner(1l,"Dipesh","dipesh@gmail.com","dipesh2901",8668259161l);
		carOwnerService.deleteCarOwner(carOwner.getId());
		verify(carOwnerRepository, timeout(1)).deleteById(carOwner.getId());
	}
	
	@Test
	public void getAllAdminsTest() {
		when(adminRepository.findAll()).thenReturn(
				Stream.of(new Admin("admin1","admin1","admin@admin.com","admin123",null))
						.collect(Collectors.toList()));
		assertEquals(1, adminService.getAllAdmins().size());
	}
	
	@Test
	public void deleteAdminTest() {
		Admin admin=new Admin(1l,"admin","admin","admin@admin.com","admin123", null);
		adminService.deleteAdminById(admin.getId());
		verify(adminRepository, timeout(1)).deleteById(admin.getId());
	}
	

}
