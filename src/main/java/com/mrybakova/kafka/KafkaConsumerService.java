package com.mrybakova.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "api-log-topic", groupId = "api-log-group")
    public void listen(ConsumerRecord<String, String> consumerRecord) {
        log.info("Received Message:  {}", consumerRecord.value());
    }
}
