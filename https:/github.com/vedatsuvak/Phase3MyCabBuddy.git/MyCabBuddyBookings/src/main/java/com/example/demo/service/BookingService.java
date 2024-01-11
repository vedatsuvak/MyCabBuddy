package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.repo.BookingRepo;

@Service
public class BookingService {

	@Autowired
	private BookingRepo repo;

	// Insert a Booking
	public Booking save(Booking booking) {
		return repo.save(booking);
	}

	// Update a Booking
	public int updateBooking(int bookingId, String bookingDate, String bookingTime, String paymentType) {
		return repo.updateBooking(bookingId, bookingDate, bookingTime, paymentType);
	}

	// Retrieve all Bookings
	public List<Booking> findAll() {
		return repo.findAll();
	}

	// Retrieve all User's Bookings
	public List<Booking> getUserBookings(int userId) {
		return repo.getUserBookings(userId);
	}

	// Retrieve a Booking by ID
	public Optional<Booking> findBookingById(int id) {
		return repo.findById(id);
	}

	// update Booking
	public int updateBooking(Booking booking) {
		int id = booking.getId();
		String bookingDate = booking.getBookingDate();
		String bookingTime = booking.getBookingTime();
		String paymentType = booking.getPaymentType();
		double totalPrice = booking.getTotalPrice();
		int userId = booking.getUserId();
		int cabId = booking.getCabId();
		String cabType = booking.getCabType();
		String driverName = booking.getDriverName();
		double driverRating = booking.getDriverRating();
		int destinationId = booking.getDestinationId();
		String source = booking.getSource();
		String destination = booking.getDestination();
		return repo.updateAll(id, bookingDate, bookingTime, paymentType, totalPrice, userId, cabId, cabType, driverName,
				driverRating, destinationId, source, destination);
	}

	// Delete a Booking by ID
	public String deleteById(int id) {
		repo.deleteById(id);
		return "Your Booking Deleted Successfully!";
	}
}
