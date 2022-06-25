package com.sakinr.patika.airportreservationsystem.model.dto;

import lombok.Data;

@Data
public class PassengerDTO {

    private String firstname;
    private String lastname;
    private String gender;
    private Integer age;
    private String phone;
    private String email;
}
