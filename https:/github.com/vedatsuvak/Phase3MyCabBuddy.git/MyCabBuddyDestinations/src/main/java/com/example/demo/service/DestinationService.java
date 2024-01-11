package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Destination;
import com.example.demo.repository.DestinationRepo;

@Service
public class DestinationService {

	@Autowired
	private DestinationRepo repo;

	// Insert a Destination
	public Destination insert(Destination destination) {
		return repo.save(destination);
	}

	// Retrieve all Destinations
	public List<Destination> getAll() {
		return repo.findAll();
	}

	// Retrieve a Destination by ID
	public Optional<Destination> findById(int id) {
		return repo.findById(id);
	}

	// Retrieve price by source and destination
	public Integer findprice(String source, String destination) {
		return repo.findprice(source, destination);
	}

	// Retrieve a Destination by source and destination
	public Destination findDestination(String source, String destination) {
		return repo.findDestination(source, destination);
	}

	// update Destination
	public Destination updateDestination(Destination destination) {
		return repo.save(destination);
	}

	// Delete a Destination by ID
	public String deleteById(int id) {
		repo.deleteById(id);
		return "Deleted";
	}

}
