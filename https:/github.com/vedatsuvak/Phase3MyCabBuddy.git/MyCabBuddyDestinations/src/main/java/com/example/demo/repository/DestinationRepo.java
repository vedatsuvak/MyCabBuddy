package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Destination;

public interface DestinationRepo extends JpaRepository<Destination, Integer> {

	String query = "select d.price from Destination d where d.source=?1 and d.destination=?2";

	@Query(query)
	Integer findprice(String source, String destination);

	String query1 = "select d from Destination d where d.source=?1 and d.destination=?2";

	@Query(query1)
	Destination findDestination(String source, String destination);

}
