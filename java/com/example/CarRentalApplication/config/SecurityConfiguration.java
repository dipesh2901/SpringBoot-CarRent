package com.example.CarRentalApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.CarRentalApplication.service.AdminService;
import com.example.CarRentalApplication.service.CustomerService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CustomerService customerService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(adminService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/registration**", "/adminRegistration", "/car", "/cars", "/carForm/new", "/customer**",
						"/saveCustomer", "/customerLogin", "/customers", "/customerForm", "/customerForm/new",
						"/showFormForCustomerUpdate/{id}", "/carOwner**", "/carOwner/CarOwnerModule", "/carOwnerLogin",
						"/carOwnerPersonalModule", "/customerRegistration", "/loginCustomer", "/js/**", "/css/**",
						"/img/**", "/rentCar", "/returnRentedCar/{id}", "/carShowroom", "/carRent/new", "/carRents",
						"/rentCarForm**", "/AboutUs", "/ContactUs", "/deleteCustomer/{id}",
						"/showFormForCarUpdate/{id}", "/saveCar","/deleteCar/{id}")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
				.logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll();
	}

}
