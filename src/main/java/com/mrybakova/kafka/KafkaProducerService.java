package com.mrybakova.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
//       kafkaTemplate.send("api-log-topic", message);

        CompletableFuture<SendResult<String, String>> sendResultCompletableFuture = kafkaTemplate.send("api-log-topic", message);
        sendResultCompletableFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("The message is sent {}", message);
            } else {
                log.error("Unable to send {}", message);
            }
        });
    }
}
