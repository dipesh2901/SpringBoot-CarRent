package com.example.CarRentalApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.CarRentalApplication.domain.Admin;
import com.example.CarRentalApplication.dto.AdminRegistrationDto;

public interface AdminService extends UserDetailsService{
	Admin save(AdminRegistrationDto registrationDto);
	Admin getAdminById(long id);
	void deleteAdminById(long id);
	List<Admin> getAllAdmins();
	Page<Admin> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
