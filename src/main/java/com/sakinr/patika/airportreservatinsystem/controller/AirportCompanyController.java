package com.sakinr.patika.airportreservatinsystem.controller;


import com.sakinr.patika.airportreservatinsystem.model.AirportCompany;
import com.sakinr.patika.airportreservatinsystem.model.Flight;
import com.sakinr.patika.airportreservatinsystem.model.Ticket;
import com.sakinr.patika.airportreservatinsystem.service.AirportCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport-company")
public class AirportCompanyController {

    private final AirportCompanyService airportCompanyService;

    @GetMapping
    public String welcome() {
        return "Welcome to Airport Service!";
    }

    @GetMapping(value = "/all")
    public List<AirportCompany> getAllAirportCompanies() {
        return airportCompanyService.getAllAirportCompanies();
    }

    @GetMapping(value = "/{id}")
    public AirportCompany getAirportCompany(@PathVariable @Min(1) Integer id) {
        return airportCompanyService.getAirportCompany(id);
    }

    @PostMapping(value = "/create")
    public void saveAirportCompany(@Valid @RequestBody AirportCompany airportCompany) {
        airportCompanyService.addAirportCompany(airportCompany);
    }

    @PutMapping(value = "/update")
    public AirportCompany updateAirportCompany(@RequestBody AirportCompany airportCompany) {
        return airportCompanyService.updateAirportCompany(airportCompany);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteAirportCompany(@RequestParam @Min(1) Integer id) {
        return airportCompanyService.deleteAirportCompany(id);
    }

    @PostMapping(value = "/add-flight")
    public boolean addNewFlight(@RequestParam @Min(1) Integer organizationId,
                                @RequestParam @Min(1) Integer flightId) {
        return airportCompanyService.addNewFlight(organizationId, flightId);
    }

    @PostMapping(value = "/buy-ticket")
    public Ticket buyTicket(@RequestParam @Min(1) Integer flightId, @RequestParam @Min(1) Integer passengerId) {
        return airportCompanyService.buyTicketForFlight(flightId, passengerId);
    }

    @PostMapping(value = "/cancel-ticket")
    public boolean cancelTicket(@RequestParam @Min(1) Integer ticketId) {
        return airportCompanyService.cancelTicket(ticketId);
    }

    @PostMapping(value = "/search-ticket")
    public Ticket searchTicket(@RequestParam @Min(1) Integer ticketId) {
        return airportCompanyService.searchTicket(ticketId);
    }

    @GetMapping("/by-airport-company/{airportCompanyId}")
    public List<Flight> getAllFlightByAirportCompany(@PathVariable Integer airportCompanyId) {
        return airportCompanyService.getAllFlightByAirportCompany(airportCompanyId);
    }

}
