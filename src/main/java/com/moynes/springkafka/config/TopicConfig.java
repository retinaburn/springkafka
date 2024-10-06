package com.moynes.springkafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class TopicConfig {

    @Value("${topics.avro}")
    public String avro;

    public String avro(){
        return avro;
    }
}
