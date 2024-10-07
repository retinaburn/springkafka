package com.moynes.springkafka;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import com.moynes.springkafka.avro.model.generated.User;
import com.moynes.springkafka.config.TopicConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@SpringBootTest
public class AvroProducer{

    @Autowired
    KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    TopicConfig topics;

    @Test
    void test() throws Exception{
        User user = new User();
        user.setFavoriteColor("blue");
        user.setFavoriteNumber(1234);
        user.setName("username");
        CompletableFuture<SendResult<String, User>> future = kafkaTemplate.send(topics.getAvro(), "1234", user);
        SendResult<String, User> result = future.get();

        log.debug("Produced to {} {}:{}", result.getRecordMetadata().topic(), result.getRecordMetadata().partition(), result.getRecordMetadata().offset());

    }

}
