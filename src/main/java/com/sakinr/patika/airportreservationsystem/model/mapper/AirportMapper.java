package com.sakinr.patika.airportreservationsystem.model.mapper;

import com.sakinr.patika.airportreservationsystem.model.entity.Airport;
import com.sakinr.patika.airportreservationsystem.model.AirportDTO;

// Sample user defined Mapper
public class AirportMapper {

    public static AirportDTO toDto(Airport airport) {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setName(airport.getName());
        airportDTO.setAddresses(airport.formatToAddressList());
        return airportDTO;
    }

    public static Airport toEntity(AirportDTO airportDTO) {
        Airport airport = new Airport();
        airport.setName(airportDTO.getName());
        airport.setAddress(airportDTO.formatAddresses());
        return airport;
    }

}
