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
import com.example.CarRentalApplication.domain.CarOwner;
import com.example.CarRentalApplication.domain.Role;
import com.example.CarRentalApplication.dto.CarOwnerRegistrationDto;
import com.example.CarRentalApplication.persistence.CarOwnerRepository;
import com.example.CarRentalApplication.service.CarOwnerService;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {

	@Autowired
	private CarOwnerRepository carOwnerRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Optional<CarOwner> showCarOwnerById(Long id) {
		return carOwnerRepository.findById(id);
	}

	@Override
	public void updateCarOwner(Long id, CarOwner carOwn) {
		carOwnerRepository.findById(id).map(carOwner -> {
			carOwner.setName(carOwn.getName());
			carOwner.setMobile(carOwn.getMobile());
			carOwner.setEmail(carOwn.getEmail());
			return carOwnerRepository.save(carOwner);
		});

	}

	@Override
	public void deleteCarOwner(Long id) {
		carOwnerRepository.deleteById(id);
	}

	@Override
	public List<CarOwner> showAllCarOwner() {
		return carOwnerRepository.findAll();
	}

	@Override
	public CarOwner getCarOwnerByEmail(String email) {
		return carOwnerRepository.findByEmail(email);
	}

	
	

	@Override
	public CarOwner saveCarOwner(CarOwner carOwner) {
		return carOwnerRepository.save(carOwner);

	}

	@Override
	public Page<CarOwner> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.carOwnerRepository.findAll(pageable);
	}

	@Override
	public CarOwner getCarOwnerByPassword(String password) {
		return carOwnerRepository.findByPassword(password);
	}

}
