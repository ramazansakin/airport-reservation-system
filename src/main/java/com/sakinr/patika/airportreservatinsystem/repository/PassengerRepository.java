package com.sakinr.patika.airportreservatinsystem.repository;

import com.sakinr.patika.airportreservatinsystem.model.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
