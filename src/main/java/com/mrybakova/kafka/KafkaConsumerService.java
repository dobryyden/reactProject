package com.mrybakova.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "api-log-topic", groupId = "api-log-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received Message: " + record.value());
    }
}
