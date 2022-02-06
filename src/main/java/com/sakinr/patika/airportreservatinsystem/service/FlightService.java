package com.sakinr.patika.airportreservatinsystem.service;


import com.sakinr.patika.airportreservatinsystem.model.entity.Flight;

import java.util.Date;
import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlight(Integer id);

    void addFlight(Flight flight);

    Flight updateFlight(Flight flight);

    boolean deleteFlight(Integer id);

    List<Flight> getAllFlightsDepartureDateBetween(Date start, Date end);

    Flight getFlightByCode(String code);

}
