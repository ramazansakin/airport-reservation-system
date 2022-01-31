package com.sakinr.patika.airportreservatinsystem.service;


import com.sakinr.patika.airportreservatinsystem.model.AirportCompany;
import com.sakinr.patika.airportreservatinsystem.model.Flight;
import com.sakinr.patika.airportreservatinsystem.model.Ticket;

import java.util.List;

public interface AirportCompanyService {
    List<AirportCompany> getAllAirportCompanies();

    AirportCompany getAirportCompany(Integer id);

    void addAirportCompany(AirportCompany airportCompany);

    AirportCompany updateAirportCompany(AirportCompany airportCompany);

    boolean deleteAirportCompany(Integer id);

    boolean addNewFlight(Integer airport_company_id, Integer flight_id);

    Ticket buyTicketForFlight(Integer flight_id, Integer passenger_id);

    boolean cancelTicket(Integer ticket_id);

    Ticket searchTicket(Integer ticket_id);

    List<Flight> getAllFlightByAirportCompany(Integer airport_company_id);
}
