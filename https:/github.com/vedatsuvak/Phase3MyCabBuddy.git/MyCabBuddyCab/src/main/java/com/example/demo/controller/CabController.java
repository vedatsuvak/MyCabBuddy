package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cab;
import com.example.demo.service.CabService;

@RestController
//spring boot app is open to gt accessed for any external applciation 
//@CrossOrigin(origins = "localhost:4200")
@CrossOrigin(origins = "*")
public class CabController {

	@Autowired
	private CabService service;

	@PostMapping("/register")
	public String register(@RequestBody Cab cab) {
		service.save(cab);
		return "Hey " + cab.getCabType() + " Cab, registered successfully :)";

	}

	@GetMapping("/getAllCabs")
	public List<Cab> findAllCabs() {
		return service.findAll();
	}

	@GetMapping("/findDrivers/{cabType}")
	public List<Cab> findDrivers(@PathVariable String cabType) {
		return service.findDrivers(cabType);
	}

	@GetMapping("/findDriverByName/{driverName}")
	public Cab findDriverByName(@PathVariable String driverName) {
		return service.findDriverByName(driverName);
	}

	@GetMapping("/getCab/{id}")
	public Cab findCab(@PathVariable int id) {
		return service.findById(id).orElse(null);
	}

	@PutMapping("/update")
	public String updateCab(@RequestBody Cab cab) {
		service.updateCab(cab);
		return "Hey Cab:" + cab.getId() + ", successfully updated!";
	}

	@DeleteMapping("/cancel/{id}")
	public String cancelregistartion(@PathVariable int id) {
		service.deleteById(id);
		return "Cab deleted successfully";
	}
}
