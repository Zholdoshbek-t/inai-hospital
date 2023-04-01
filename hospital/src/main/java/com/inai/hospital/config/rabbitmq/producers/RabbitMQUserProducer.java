package com.inai.hospital.config.rabbitmq.producers;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.enums.Status;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitMQUserProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQUserProducer.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.user.routing.key}")
    private String routingKey;

    public void sendMessage(Request request){
        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | RabbitMQDiseaseProducer | BODY: {}",
                UUID.randomUUID(), Status.SUCCESS, request);

        rabbitTemplate.convertAndSend(exchange, routingKey, request);
    }
}
