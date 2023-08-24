package com.sakinr.patika.airportreservationsystem.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class UserLoginDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
