package com.sakinr.patika.airportreservationsystem.controller;


import com.sakinr.patika.airportreservationsystem.exception.QuotaIsFullException;
import com.sakinr.patika.airportreservationsystem.model.entity.AirportCompany;
import com.sakinr.patika.airportreservationsystem.model.entity.Flight;
import com.sakinr.patika.airportreservationsystem.model.entity.Ticket;
import com.sakinr.patika.airportreservationsystem.service.AirportCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> buyTicket(@RequestParam @Min(1) Integer flightId, @RequestParam @Min(1) Integer passengerId) {
        ResponseEntity<?> response;
        try {
            Ticket ticket = airportCompanyService.buyTicketForFlight(flightId, passengerId);
            response = new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (QuotaIsFullException exception) {
            response = new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
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
