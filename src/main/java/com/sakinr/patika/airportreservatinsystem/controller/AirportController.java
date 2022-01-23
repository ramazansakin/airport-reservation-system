package com.sakinr.patika.airportreservatinsystem.controller;

import com.sakinr.patika.airportreservatinsystem.model.Airport;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    private List<Airport> airports = new ArrayList<>();

    {
        airports.add(new Airport("Airport 1"));
        airports.add(new Airport("Airport 2"));
    }

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello Patika - PayCore Bootcampers!";
    }

    @GetMapping(path = "/all")
    public List<Airport> getAllAirports() {
        return airports;
    }

    @PostMapping(path = "/add")
    public boolean addAirport(@RequestBody Airport airport) {
        return airports.add(airport);
    }


}
