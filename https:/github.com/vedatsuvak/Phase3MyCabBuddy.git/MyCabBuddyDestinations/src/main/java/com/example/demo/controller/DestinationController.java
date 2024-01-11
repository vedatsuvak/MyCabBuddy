package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Destination;
import com.example.demo.service.DestinationService;

@RestController
//spring boot app is open to gt accessed for any external applciation 
//@CrossOrigin(origins = "localhost:4200")
@CrossOrigin(origins = "*")
public class DestinationController {

	@Autowired
	private DestinationService service;

	@PostMapping("/insert")
	public String insert(@RequestBody Destination destination) {
		service.insert(destination);
		return "Hey destination:" + destination.getId() + ", successfully added!";

	}

	@PostMapping("/update")
	public String updateDestination(@RequestBody Destination destination) {
		service.updateDestination(destination);
		return "Hey destination:" + destination.getId() + ", successfully updated!";

	}

	@GetMapping("/getAllDestinations")
	public List<Destination> findAllDestinations() {
		return service.getAll();
	}

	@GetMapping("/getOne/{id}")
	public Destination findDestination(@PathVariable int id) {
		return service.findById(id).orElse(null);
	}

	@GetMapping("/getPrice/{source}/{destination}")
	public Integer findprice(@PathVariable String source, @PathVariable String destination) {
		int price;
		price = service.findprice(source, destination);
		return price;
	}

	@GetMapping("/findDestination/{source}/{destination}")
	public Destination findDestination(@PathVariable String source, @PathVariable String destination) {
		return service.findDestination(source, destination);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		service.deleteById(id);
		return "Destination deleted successfully";
	}
}
