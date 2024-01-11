package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cab;
import com.example.demo.repository.CabRepo;

@Service
public class CabService {

	@Autowired
	private CabRepo repo;

	// Insert a Cab
	public Cab save(Cab cab) {
		return repo.save(cab);
	}

	// Retrieve all Cabs
	public List<Cab> findAll() {
		return repo.findAll();
	}

	// Retrieve Cab list by type
	public List<Cab> findDrivers(String cabType) {
		return repo.findDrivers(cabType);
	}

	// Retrieve a Cab by driver name
	public Cab findDriverByName(String driverName) {
		return repo.findDriverByName(driverName);
	}

	// Retrieve a Cab by ID
	public Optional<Cab> findById(int id) {
		return repo.findById(id);
	}

	// update Cab
	public int updateCab(Cab cab) {
		int id = cab.getId();
		String cabType = cab.getCabType();
		String driverName = cab.getDriverName();
		double driverRating = cab.getDriverRating();
		int cabCapacity = cab.getCabCapacity();
		double pricePercentage = cab.getPricePercentage();
		return repo.update(id, cabType, driverName, driverRating, cabCapacity, pricePercentage);
	}

	// Delete a Cab by ID
	public String deleteById(int id) {
		repo.deleteById(id);
		return "Deleted";
	}
}
