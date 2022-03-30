package com.sakinr.patika.airportreservationsystem.model.mapper;

import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservationsystem.model.PassengerDto;
import org.mapstruct.Mapper;

// Sample mapstruct defined mapper
@Mapper
public interface PassengerMapper {
    PassengerDto toDto(Passenger entity);

    Passenger toEntity(PassengerDto dto);
}
