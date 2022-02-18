package com.sakinr.patika.airportreservatinsystem.repository;

import com.sakinr.patika.airportreservatinsystem.model.entity.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {

    Flight getByCode(String code);

    List<Flight> getAllByDepartureDateBetween(Date startDate, Date endDate);

}