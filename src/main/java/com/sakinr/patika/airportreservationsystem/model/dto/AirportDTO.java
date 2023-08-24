package com.sakinr.patika.airportreservationsystem.model.dto;

import com.sakinr.patika.airportreservationsystem.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.StringJoiner;

@Data
public class AirportDTO {

    @NotBlank
    private String name;

    @NotEmpty
    private List<@Valid Address> addresses;

    public String formatAddresses() {
        StringJoiner strJoiner = new StringJoiner(" // ");
        getAddresses().forEach(address -> strJoiner.add(address.dbFormat()));
        return strJoiner.toString();
    }

}
