package com.sakinr.patika.airportreservatinsystem.repository;

import com.sakinr.patika.airportreservatinsystem.model.entity.Airport;
import com.sakinr.patika.airportreservatinsystem.model.entity.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {

    Route getFirstById(String id);

    Route findByDepartureAirport(Airport departure_airport);

}
