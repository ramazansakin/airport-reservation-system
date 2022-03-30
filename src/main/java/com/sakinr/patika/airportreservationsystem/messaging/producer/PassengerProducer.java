package com.sakinr.patika.airportreservationsystem.messaging.producer;

import com.sakinr.patika.airportreservationsystem.config.RabbitMQConfig;
import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservationsystem.service.PassengerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messaging/publish")
public class PassengerProducer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/{message}")
    public String publishMessage(@PathVariable String message) {
        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
        return "Success";
    }

    @PostMapping("/passenger/{id}")
    public String publishPassenger(@PathVariable Integer id) {
        Passenger passenger = passengerService.getPassenger(id);
        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, passenger);
        return "Success";
    }
}
