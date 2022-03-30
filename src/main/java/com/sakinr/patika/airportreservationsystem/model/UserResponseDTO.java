package com.sakinr.patika.airportreservationsystem.model;

import com.sakinr.patika.airportreservationsystem.model.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    List<Role> roles;

}
