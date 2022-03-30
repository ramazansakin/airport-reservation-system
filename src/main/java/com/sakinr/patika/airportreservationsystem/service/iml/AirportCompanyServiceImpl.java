package com.sakinr.patika.airportreservationsystem.service.iml;

import com.sakinr.patika.airportreservationsystem.exception.NotFoundException;
import com.sakinr.patika.airportreservationsystem.exception.QuotaIsFullException;
import com.sakinr.patika.airportreservationsystem.model.entity.AirportCompany;
import com.sakinr.patika.airportreservationsystem.model.entity.Flight;
import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservationsystem.model.entity.Ticket;
import com.sakinr.patika.airportreservationsystem.repository.AirportCompanyRepository;
import com.sakinr.patika.airportreservationsystem.service.AirportCompanyService;
import com.sakinr.patika.airportreservationsystem.service.FlightService;
import com.sakinr.patika.airportreservationsystem.service.PassengerService;
import com.sakinr.patika.airportreservationsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AirportCompanyServiceImpl implements AirportCompanyService {

    private final AirportCompanyRepository airportCompanyRepository;

    private final FlightService flightService;

    private final TicketService ticketService;

    private final PassengerService passengerService;

    @Override
    public List<AirportCompany> getAllAirportCompanies() {
        return airportCompanyRepository.findAll();
    }

    @Override
    public AirportCompany getAirportCompany(Integer id) {
        Optional<AirportCompany> byId = airportCompanyRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("AirportCompany"));
    }

    @Override
    public void addAirportCompany(AirportCompany airportCompany) {
        airportCompanyRepository.save(airportCompany);
    }

    @Override
    public AirportCompany updateAirportCompany(AirportCompany airportCompany) {
        return airportCompanyRepository.save(airportCompany);
    }

    @Override
    public boolean addNewFlight(Integer airportCompanyId, Integer flightId) {
        AirportCompany airportCompany = getAirportCompany(airportCompanyId);
        Flight flight = flightService.getFlight(flightId);
        flight.setAirportCompany(airportCompany);
        airportCompanyRepository.save(airportCompany);
        return true;
    }

    @Override
    public boolean deleteAirportCompany(Integer id) {
        AirportCompany airportCompany = getAirportCompany(id);
        airportCompanyRepository.delete(airportCompany);
        return true;
    }

    @Override
    public boolean cancelTicket(Integer ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        Flight flight = flightService.getFlight(ticket.getFlight().getId());
        Ticket ticket1 = flight.getTickets().stream()
                .filter(t -> t.getId().equals(ticket.getId()))
                .findFirst().orElseThrow(() -> new NotFoundException("Ticket"));
        flight.getTickets().remove(ticket1);
        flightService.updateFlight(flight);
        ticketService.deleteTicket(ticket.getId());
        return true;
    }

    @Override
    public Ticket searchTicket(Integer ticketId) {
        return ticketService.getTicket(ticketId);
    }

    @Override
    public Ticket buyTicketForFlight(Integer flightId, Integer passengerId) {
        Passenger passenger = passengerService.getPassenger(passengerId);
        Flight flight = flightService.getFlight(flightId);

        if (flight.getTickets().size() < flight.getQuota()) {
            Ticket newTicket = new Ticket();
            newTicket.setPassenger(passenger);
            newTicket.setFlight(flight);
            ticketService.addTicket(newTicket);

            int rate = (flight.getQuota() * 10) / 100;
            if (flight.getTickets().size() > rate) {
                int newPrice = flight.getPrice() + (flight.getPrice() * ((flight.getTickets().size() / rate) * 10)) / 100;
                flight.setPrice(newPrice);
                flightService.updateFlight(flight);
            }
            return newTicket;
        }

        throw new QuotaIsFullException(flight.getCode());
    }

    @Override
    public List<Flight> getAllFlightByAirportCompany(Integer airportCompanyId) {
        AirportCompany airportCompany = getAirportCompany(airportCompanyId);
        return airportCompany.getFlights();
    }

}
