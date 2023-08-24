package com.sakinr.patika.airportreservationsystem.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDataDTO implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    @Email(message = "Email not valid")
    private String email;

    @NotBlank
    private String password;

}
