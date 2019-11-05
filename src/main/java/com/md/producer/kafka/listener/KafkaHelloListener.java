package com.md.producer.kafka.listener;

import com.md.producer.User;
import com.md.producer.entity.Hello;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaHelloListener {

    @KafkaListener(
            topics = "JsonTopic",
            containerFactory = "helloKafkaListenerFactory")
    public void jsonListener(Hello hello) {
        System.out.println("Message Received: " + hello.getId() + ";" + hello.getName() + ";" + hello.getUuid());
    }

    @KafkaListener(
            topics = "AvroTopic",
            containerFactory = "userKafkaListenerFactory")
    public void avroListener(User user) {
        System.out.println("Message Received: " + user.getName());
    }
}
