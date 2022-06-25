package com.sakinr.patika.airportreservationsystem.model.mapper;

import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservationsystem.model.dto.PassengerDTO;
import org.mapstruct.Mapper;

// Sample mapstruct defined mapper
@Mapper
public interface PassengerMapper {
    PassengerDTO toDto(Passenger entity);

    Passenger toEntity(PassengerDTO dto);
}
