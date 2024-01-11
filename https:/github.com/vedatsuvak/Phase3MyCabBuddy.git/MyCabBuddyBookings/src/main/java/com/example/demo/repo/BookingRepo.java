package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

	String query = "SELECT b FROM Booking b WHERE b.userId = ?1";

	@Query(query)
	List<Booking> getUserBookings(int userId);

	String queryUpdate = "UPDATE Booking b SET b.bookingDate = ?2, b.bookingTime = ?3, b.paymentType = ?4 WHERE b.id = ?1";

	@Transactional
	@Modifying
	@Query(queryUpdate)
	int updateBooking(int bookingId, String bookingDate, String bookingTime, String paymentType);

	String queryUpdateAll = "UPDATE Booking b SET b.bookingDate = ?2, b.bookingTime = ?3, b.paymentType = ?4, b.totalPrice = ?5, b.userId = ?6, b.cabId = ?7, b.cabType = ?8, b.driverName = ?9, b.driverRating = ?10, b.destinationId = ?11, b.source = ?12, b.destination = ?13 WHERE b.id = ?1";

	@Transactional
	@Modifying
	@Query(queryUpdateAll)
	int updateAll(int id, String bookingDate, String bookingTime, String paymentType, double totalPrice, int userId,
			int cabId, String cabType, String driverName, double driverRating, int destinationId, String source,
			String destination);

}
