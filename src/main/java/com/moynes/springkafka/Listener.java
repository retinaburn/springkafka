package com.moynes.springkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

import com.moynes.springkafka.avro.model.generated.User;
import com.moynes.springkafka.config.TopicConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Listener {

    @Autowired
    ConcurrentKafkaListenerContainerFactory<String, User> containerFactory;

    @Autowired
    private TopicConfig topics;
    
    @KafkaListener(topics = "#{topicConfig.avro()}")
    public void listener(ConsumerRecord<String, String> record) {
        log.debug("Listener read: {}", record);
    }
}
