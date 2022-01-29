package com.sakinr.patika.airportreservatinsystem.controller;

import com.sakinr.patika.airportreservatinsystem.model.Airport;
import com.sakinr.patika.airportreservatinsystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello Patika - PayCore Bootcampers!";
    }

    @GetMapping(path = "/all")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @PostMapping(path = "/get")
    public Airport getAirport(@RequestParam Integer id) {
        return airportService.getAirport(id);
    }

    @PostMapping(path = "/add")
    public boolean addAirport(@RequestBody Airport airport) {
        return airportService.addAirport(airport);
    }

    @PutMapping(value = "/update")
    public Airport updateAirport(@RequestBody Airport airport) {
        return airportService.updateAirport(airport);
    }

    @DeleteMapping(value = "/delete/id={id}")
    public boolean deleteAirport(@PathVariable Integer id) {
        return airportService.deleteAirport(id);
    }

}
