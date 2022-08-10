package com.sakinr.patika.airportreservationsystem.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
