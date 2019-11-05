package com.md.producer.kafka.config;

import com.md.producer.User;
import com.md.producer.entity.Hello;
import com.md.producer.kafka.avro.AvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
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

    //<String, String> Producer Config
    @Bean
    public ProducerFactory<String, String> producerStringStringFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, String> kafkaStringStringTemplate() {
        return new KafkaTemplate<>(producerStringStringFactory());
    }
    //End of <String, String> Consumer Config

    //<String, Json> Producer Config
    @Bean
    public ProducerFactory<String, Hello> producerJsonFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, Hello> kafkaStringJsonTemplate() {
        return new KafkaTemplate<>(producerJsonFactory());
    }
    //End of <String, Json> Consumer Config


    //<String, Avro> Producer Config
    @Bean
    public ProducerFactory<String, User> producerAvroFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, User> kafkaAvroTemplate() {
        return new KafkaTemplate<>(producerAvroFactory());
    }
    //End of <String, Avro> Consumer Config
}