package com.sakinr.patika.airportreservationsystem.controller;

import com.sakinr.patika.airportreservationsystem.model.entity.Flight;
import com.sakinr.patika.airportreservationsystem.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;


//@PreAuthorize("isAuthenticated()")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public String welcome() {
        return "Welcome to Flight Service!";
    }

    @GetMapping(value = "/all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping(value = "/{id}")
    public Flight getFlight(@PathVariable @Min(1) Integer id) {
        return flightService.getFlight(id);
    }

    @PostMapping(value = "/create")
    public void saveFlight(@Valid @RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @PutMapping(value = "/update")
    public Flight updateFlight(@Valid @RequestBody Flight flight) {
        return flightService.updateFlight(flight);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteFlight(@RequestParam @Min(1) Integer id) {
        return flightService.deleteFlight(id);
    }

    @GetMapping(value = "/all/departure-data-between")
    public List<Flight> getAllFlightsDepartureDateBetween(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date start,
                                                          @Valid @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date end) {
        return flightService.getAllFlightsDepartureDateBetween(start, end);
    }

    // We can write an annotation and validator to check
    // whether this is a valid code or not
    @GetMapping("/by-code/{code}")
    public Flight getFlightByCode(@PathVariable String code) {
        return flightService.getFlightByCode(code);
    }

}
