package com.sakinr.patika.airportreservatinsystem.service;

import com.sakinr.patika.airportreservatinsystem.model.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> getAllAirports();

    Airport getAirport(String id);

    void addAirport(Airport airport);

    Airport updateAirport(String id, Airport airport);

    boolean deleteAirport(String id);
}
