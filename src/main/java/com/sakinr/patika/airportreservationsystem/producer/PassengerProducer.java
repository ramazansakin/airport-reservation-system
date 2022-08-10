package com.sakinr.patika.airportreservationsystem.producer;

import com.sakinr.patika.airportreservationsystem.config.KafkaProducerConfig;
import com.sakinr.patika.airportreservationsystem.model.dto.PassengerDTO;
import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservationsystem.model.mapper.PassengerMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Component
@Profile({"docker"})
public class PassengerProducer {

    private final KafkaTemplate<String, Object> passengerKafkaTemplate;
    private static final Queue<Passenger> passengerAnalyzerProducerQueue = new LinkedList<>();
    private static final PassengerMapper PASSENGER_MAPPER = Mappers.getMapper(PassengerMapper.class);

    private static final long PASSENGER_PRODUCING_TIME_SCHEDULE_MILLIS = 30 * 1000; // 30 s

    public PassengerProducer(@Qualifier("kafkaPassengerTemplate") KafkaTemplate<String, Object> customKafkaTemplate) {
        this.passengerKafkaTemplate = customKafkaTemplate;
    }

    public static void addToQueue(Passenger newPassenger) {
        passengerAnalyzerProducerQueue.add(newPassenger);
    }

    // @Scheduled Method rules.
    // 1 - should have the void return type,
    // 2 - should not accept any parameters.
    @Scheduled(fixedRate = PASSENGER_PRODUCING_TIME_SCHEDULE_MILLIS)
    public void produceNewPassengerEvents() {
        // check is there any new stored passenger on db
        log.info("produceNewPassengerEvents scheduled method called.");
        while (!passengerAnalyzerProducerQueue.isEmpty()) {
            Passenger currentPassenger = passengerAnalyzerProducerQueue.remove();
            PassengerDTO passengerDTO = PASSENGER_MAPPER.toDto(currentPassenger);
            log.info("Passenger sent to Kafka : {}", currentPassenger);
            passengerKafkaTemplate.send(KafkaProducerConfig.PASSENGER_TOPIC, passengerDTO);
        }

    }

}
