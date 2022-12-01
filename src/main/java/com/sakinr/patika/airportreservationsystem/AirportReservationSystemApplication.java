package com.sakinr.patika.airportreservationsystem;

import com.sakinr.patika.airportreservationsystem.exception.handler.RestTemplateResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.sakinr.patika.airportreservationsystem")
@EnableScheduling
public class AirportReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportReservationSystemApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate;
    }

}