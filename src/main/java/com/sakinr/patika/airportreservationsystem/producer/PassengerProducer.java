package com.sakinr.patika.airportreservationsystem.producer;

import com.sakinr.patika.airportreservationsystem.config.KafkaProducerConfig;
import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Component
public class PassengerProducer {

    private final KafkaTemplate<String, Object> passengerKafkaTemplate;
    private static final Queue<Passenger> passengerAnalyzerProducerQueue = new LinkedList<>();

    private static final long PASSENGER_PRODUCING_TIME_SCHEDULE_MILLIS = 60 * 1000; // 1 min

    public PassengerProducer(@Qualifier("kafkaPassengerTemplate") KafkaTemplate<String, Object> customKafkaTemplate) {
        this.passengerKafkaTemplate = customKafkaTemplate;
    }

    public static void addToQueue(Passenger newPassenger) {
        passengerAnalyzerProducerQueue.add(newPassenger);
    }

    @Scheduled(fixedRate = PASSENGER_PRODUCING_TIME_SCHEDULE_MILLIS)
    public void produceNewPassengerEvents() {
        // check is there any new stored passenger on db
        while (!passengerAnalyzerProducerQueue.isEmpty()) {
            Passenger currentPassenger = passengerAnalyzerProducerQueue.remove();
            log.info("Passenger sent to Kafka : {}", currentPassenger);
            passengerKafkaTemplate.send(KafkaProducerConfig.PASSENGER_TOPIC, currentPassenger);
        }

    }

}
