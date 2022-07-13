package com.sakinr.patika.airportreservationsystem.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    public static final String PASSENGER_TOPIC = "student-topic";

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public NewTopic passengerTopic() {
        return new NewTopic(PASSENGER_TOPIC, 1, (short) 1);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaSimpleMessageTemplate() {
        return new KafkaTemplate<>(simpleMessageProducerFactory());
    }

    // Default Factory is To send String messages
    private ProducerFactory<String, String> simpleMessageProducerFactory() {
        final Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // Passenger producer factory & template
    @Bean
    public KafkaTemplate<String, Object> kafkaPassengerTemplate() {
        return new KafkaTemplate<>(passengerProducerFactory());
    }

    // Custom Factory is To send custom objects / Student
    private ProducerFactory<String, Object> passengerProducerFactory() {
        final Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

}
