package com.sakinr.patika.airportreservationsystem.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
