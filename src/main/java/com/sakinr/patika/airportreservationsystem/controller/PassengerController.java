package com.sakinr.patika.airportreservationsystem.controller;


import com.sakinr.patika.airportreservationsystem.model.dto.PassengerDTO;
import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservationsystem.model.mapper.PassengerMapper;
import com.sakinr.patika.airportreservationsystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger")
public class PassengerController {

    private final PassengerService passengerService;
    private static final PassengerMapper PASSENGER_MAPPER = Mappers.getMapper(PassengerMapper.class);

    @GetMapping
    public String welcome() {
        return "Welcome to Passenger Service!";
    }

    @GetMapping(value = "/all")
    public List<PassengerDTO> getAllPassengers() {
        List<Passenger> allPassengers = passengerService.getAllPassengers();
        return allPassengers.stream().map(PASSENGER_MAPPER::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public PassengerDTO getPassenger(@PathVariable @Min(1) Integer id) {
        return PASSENGER_MAPPER.toDto(passengerService.getPassenger(id));
    }

    @PostMapping(value = "/create")
    public void savePassenger(@Valid @RequestBody PassengerDTO passenger) {
        passengerService.addPassenger(PASSENGER_MAPPER.toEntity(passenger));
    }

    // PUT vs PATCH
    @PutMapping(value = "/update/{id}")
    public PassengerDTO updatePassenger(@PathVariable @Min(1) final Integer id,
                                        @Valid @RequestBody Passenger passenger) {
        return PASSENGER_MAPPER.toDto(passengerService.updatePassenger(id, passenger));
    }

    @PatchMapping(value = "/update/{id}")
    public PassengerDTO patchPassenger(@PathVariable @Min(1) final Integer id,
                                       @Valid @RequestBody Passenger passenger) {
        return PASSENGER_MAPPER.toDto(passengerService.updatePassenger(id, passenger));
    }

    @DeleteMapping(value = "/delete")
    public boolean deletePassenger(@RequestParam @Min(1) Integer id) {
        return passengerService.deletePassenger(id);
    }

}
