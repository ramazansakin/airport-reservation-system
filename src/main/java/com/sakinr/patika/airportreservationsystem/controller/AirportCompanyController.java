package com.sakinr.patika.airportreservationsystem.controller;


import com.sakinr.patika.airportreservationsystem.model.entity.AirportCompany;
import com.sakinr.patika.airportreservationsystem.model.entity.Flight;
import com.sakinr.patika.airportreservationsystem.model.entity.Ticket;
import com.sakinr.patika.airportreservationsystem.service.AirportCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport-company")
public class AirportCompanyController {

    private final AirportCompanyService airportCompanyService;

    @GetMapping
    public String welcome() {
        return "Welcome to Airportcompany Service!";
    }

    @GetMapping(value = "/all")
    public List<AirportCompany> getAllAirportCompanies() {
        return airportCompanyService.getAllAirportCompanies();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAirportCompany(@PathVariable @Min(1) Integer id) {
        AirportCompany airportCompany = airportCompanyService.getAirportCompany(id);
        ResponseEntity<?> response = new ResponseEntity<>(airportCompany, HttpStatus.OK);
        log.info("Response : " + response);
        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
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
    public boolean addNewFlight(@RequestParam @Min(1) Integer airportCompanyId,
                                @RequestParam @Min(1) Integer flightId) {
        return airportCompanyService.addNewFlight(airportCompanyId, flightId);
    }

    @PostMapping(value = "/buy-ticket")
    public ResponseEntity<?> buyTicket(@RequestParam @Min(1) final Integer flightId,
                                       @RequestParam @Min(1) final Integer passengerId) {
        Ticket ticket = airportCompanyService.buyTicketForFlight(flightId, passengerId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
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
