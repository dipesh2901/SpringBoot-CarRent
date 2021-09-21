package com.example.CarRentalApplication.serviceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CarRentalApplication.domain.Admin;
import com.example.CarRentalApplication.domain.Role;
import com.example.CarRentalApplication.dto.AdminRegistrationDto;
import com.example.CarRentalApplication.persistence.AdminRepository;
import com.example.CarRentalApplication.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(username);
		if (admin == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(),
				mapRolesToAuthorities(admin.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public Admin save(AdminRegistrationDto registrationDto) {
		Admin admin = new Admin(registrationDto.getFirstName(), registrationDto.getLastName(),
				registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()),
				Arrays.asList(new Role("ROLE_ADMIN")));

		return adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}
	
	@Override
	public Page<Admin> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.adminRepository.findAll(pageable);
	}

	@Override
	public Admin getAdminById(long id) {
		Optional<Admin> admin=adminRepository.findById(id);
		System.out.println("Optional Value: "+admin);
		if(admin.get()!=null) {
			return admin.get();
		}
		else {
			throw new RuntimeException("Admin not found for id :: " + id);
		}
	}

	@Override
	public void deleteAdminById(long id) {
		adminRepository.deleteById(id);
		
	}

}
