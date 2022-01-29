package com.sakinr.patika.airportreservatinsystem.service;

import com.sakinr.patika.airportreservatinsystem.model.Airport;

import java.util.List;

public interface AirportService {

    List<Airport> getAllAirports();

    Airport getAirport(Integer id);

    boolean addAirport(Airport airport);

    Airport updateAirport(String name, Airport airport);

    boolean deleteAirport(Integer id);

}
