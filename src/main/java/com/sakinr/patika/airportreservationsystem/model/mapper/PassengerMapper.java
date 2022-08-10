package com.sakinr.patika.airportreservationsystem.model.mapper;

import com.sakinr.patika.airportreservationsystem.model.dto.PassengerDTO;
import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

// Sample mapstruct defined mapper
@Mapper
public interface PassengerMapper {
    PassengerDTO toDto(Passenger entity);

    Passenger toEntity(PassengerDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void fill(Passenger source, @MappingTarget Passenger target);
}
