package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Destination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String source;
	private String destination;
	private int price;

}
