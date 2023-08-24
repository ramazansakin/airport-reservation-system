package com.sakinr.patika.airportreservationsystem.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PassengerDTO {

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String gender;
    private Integer age;
    private String phone;
    @NotBlank
    private String email;
}
