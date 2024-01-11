package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String bookingDate;
	private String bookingTime;
	private String paymentType;
	private double totalPrice;
	private int userId;
	private int cabId;
	private String cabType;
	private String driverName;
	private double driverRating;
	private int destinationId;
	private String source;
	private String destination;

}
