package com.sakinr.patika.airportreservatinsystem.service;


import com.sakinr.patika.airportreservatinsystem.model.entity.AirportCompany;
import com.sakinr.patika.airportreservatinsystem.model.entity.Flight;
import com.sakinr.patika.airportreservatinsystem.model.entity.Ticket;

import java.util.List;

public interface AirportCompanyService {
    List<AirportCompany> getAllAirportCompanies();

    AirportCompany getAirportCompany(String id);

    void addAirportCompany(AirportCompany airportCompany);

    AirportCompany updateAirportCompany(AirportCompany airportCompany);

    boolean deleteAirportCompany(String id);

    boolean addNewFlight(String airportCompanyId, String flightId);

    Ticket buyTicketForFlight(String flightId, String passengerId);

    boolean cancelTicket(String ticketId);

    Ticket searchTicket(String ticketId);

    List<Flight> getAllFlightByAirportCompany(String airportCompanyId);
}
