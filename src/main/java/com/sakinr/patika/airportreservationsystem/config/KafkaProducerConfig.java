package com.sakinr.patika.airportreservationsystem.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Profile({"docker", "local"})
public class KafkaProducerConfig {

    public static final String PASSENGER_TOPIC = "passengert";
    public static final String TEST_TOPIC = "testt";

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public NewTopic passengerTopic() {
        return new NewTopic(PASSENGER_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic testTopic() {
        return new NewTopic(TEST_TOPIC, 1, (short) 1);
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
        // NOTE : If you have a problem on conversion, you can define TYPE_MAPPINGS like below!
        configProps.put(JsonSerializer.TYPE_MAPPINGS, "passengert:com.sakinr.patika.airportreservationsystem.model.dto.PassengerDTO");

        return new DefaultKafkaProducerFactory<>(configProps);
    }

}
