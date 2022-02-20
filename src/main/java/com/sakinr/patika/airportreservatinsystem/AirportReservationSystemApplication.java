package com.sakinr.patika.airportreservatinsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AirportReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportReservationSystemApplication.class, args);
    }

    // Swagger api documentation config for base package
    @Bean
    public Docket documentApis() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(
                RequestHandlerSelectors.basePackage("com.sakinr.patika.airportreservationsystem.controller")
        ).build();
    }

}