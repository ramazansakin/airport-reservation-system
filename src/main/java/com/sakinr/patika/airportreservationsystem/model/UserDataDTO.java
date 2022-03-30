package com.sakinr.patika.airportreservationsystem.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
