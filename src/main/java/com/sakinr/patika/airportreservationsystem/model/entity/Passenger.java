package com.sakinr.patika.airportreservationsystem.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "firstname can not be null")
    private String firstname;

    @NotBlank(message = "lastname can not be null")
    private String lastname;

    //    @NotNull(message = "gender can not be null")
    private String gender;

    //    @NotNull(message = "age can not be null")
    private Integer age;

    //    @NotNull(message = "phone can not be null")
    private String phone;

    @Email
    @NotBlank
    private String email;

}
