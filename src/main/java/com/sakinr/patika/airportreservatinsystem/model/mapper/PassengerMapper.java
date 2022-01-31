package com.sakinr.patika.airportreservatinsystem.model.mapper;

import com.sakinr.patika.airportreservatinsystem.model.Passenger;
import com.sakinr.patika.airportreservatinsystem.model.PassengerDto;
import org.mapstruct.Mapper;

@Mapper
public interface PassengerMapper {
    PassengerDto toDto(Passenger entity);

    Passenger toEntity(PassengerDto dto);
}
