package com.sakinr.patika.airportreservatinsystem.model.mapper;

import com.sakinr.patika.airportreservatinsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservatinsystem.model.PassengerDto;
import org.mapstruct.Mapper;

// Sample mapstruct defined mapper
@Mapper
public interface PassengerMapper {
    PassengerDto toDto(Passenger entity);

    Passenger toEntity(PassengerDto dto);
}
