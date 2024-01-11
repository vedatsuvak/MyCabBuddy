package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;

@SpringBootTest(classes = { MyCabBuddyBookingsApplication.class, TestConfig.class })
class MyCabBuddyBookingsApplicationTests {

	@Autowired
	private BookingService bookingService;

	private Booking testBooking;

	@BeforeEach
	void setUp() {
		testBooking = new Booking();
		testBooking.setBookingDate("2023-01-01");
		testBooking.setBookingTime("12:00 PM");
		testBooking.setPaymentType("Credit Card");
		testBooking.setUserId(100);
		testBooking = bookingService.save(testBooking);
	}

	@AfterEach
	void tearDown() {
		if (testBooking != null) {
			bookingService.deleteById(testBooking.getId());
		}
	}

	@Test
	void testSaveBooking() {
		assertNotNull(testBooking);
		assertEquals(testBooking, bookingService.save(testBooking));
	}

	@Test
	void testUpdateBooking() {
		int result = bookingService.updateBooking(testBooking.getId(), testBooking.getBookingDate(),
				testBooking.getBookingTime(), testBooking.getPaymentType());
		assertEquals(1, result);
	}

	@Test
	void testFindAllBookings() {
		List<Booking> bookings = bookingService.findAll();
		assertNotNull(bookings);
		assertFalse(bookings.isEmpty());
		assertEquals(testBooking, bookings.get(bookings.size() - 1));
	}

	@Test
	void testGetUserBookings() {
		int userId = 100;
		List<Booking> userBookings = bookingService.getUserBookings(userId);
		assertNotNull(userBookings);
		assertFalse(userBookings.isEmpty());
		assertEquals(1, userBookings.size());
		assertEquals(testBooking, userBookings.get(0));
	}

	@Test
	void testFindBookingById() {
		int bookingId = testBooking.getId();
		Optional<Booking> result = bookingService.findBookingById(bookingId);
		assertTrue(result.isPresent());
		assertEquals(testBooking, result.get());
	}

	@Test
	void testFindBookingByIdNotFound() {
		int bookingId = 1;
		Optional<Booking> result = bookingService.findBookingById(bookingId);
		assertFalse(result.isPresent());
	}

	@Test
	void testUpdateBookingEntity() {
		int updatedBooking = bookingService.updateBooking(testBooking);
		assertNotNull(updatedBooking);
	}

	@Test
	void testDeleteBookingById() {
		int bookingId = testBooking.getId();
		String result = bookingService.deleteById(bookingId);
		assertEquals("Your Booking Deleted Successfully!", result);
	}
}
