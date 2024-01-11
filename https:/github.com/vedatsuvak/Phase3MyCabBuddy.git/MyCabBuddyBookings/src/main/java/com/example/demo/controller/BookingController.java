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

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;

@RestController
//spring boot app is open to gt accessed for any external applciation 
//@CrossOrigin(origins = "localhost:4200")
@CrossOrigin(origins = "*")
public class BookingController {

	@Autowired
	private BookingService service;

	@PostMapping("/bookCab")
	public Booking saveBooking(@RequestBody Booking booking) {
		System.out.println(booking);
		return service.save(booking);

	}

	@GetMapping("/getAllBookings")
	public List<Booking> findAllBookings() {
		return service.findAll();
	}

	@GetMapping("/getUserBookings/{userId}")
	public List<Booking> getUserBookings(@PathVariable int userId) {
		return service.getUserBookings(userId);
	}

	@GetMapping("/getBookingById/{id}")
	public Booking findBookingById(@PathVariable int id) {
		System.out.println(service.findBookingById(id).orElse(null));
		return service.findBookingById(id).orElse(null);
	}

	@PutMapping("/updateBooking")
	public String update(@RequestBody Booking booking) {
		service.save(booking);
		return "Hey Booking:" + booking.getId() + ", successfully updated!";
	}

	@PostMapping("/updateBooking/{bookingId}/{bookingDate}/{bookingTime}/{paymentType}")
	public String updateBooking(@PathVariable int bookingId, @PathVariable String bookingDate,
			@PathVariable String bookingTime, @PathVariable String paymentType) {
		service.updateBooking(bookingId, bookingDate, bookingTime, paymentType);
		return "Booking updated successfully!";
	}

	@DeleteMapping("/deleteBooking/{id}")
	public String cancelregistartion(@PathVariable int id) {
		service.deleteById(id);
		return "Your booking deleted successfully!";
	}
}
