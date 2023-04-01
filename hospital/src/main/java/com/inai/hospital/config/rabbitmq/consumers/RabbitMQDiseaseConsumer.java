package com.inai.hospital.config.rabbitmq.consumers;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitMQDiseaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQDiseaseConsumer.class);

    private final DiseaseService diseaseService;

    @RabbitListener(queues = {"${rabbitmq.disease.queue.name}"})
    public void consume(Request request) {
        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | RabbitMQDiseaseConsumer | BODY: {}",
                UUID.randomUUID(), Status.SUCCESS, request);

        switch (request.getDiseaseApi()) {
            case "create":
                diseaseService.createDisease(request);
                break;
            case "update":
                diseaseService.updateDisease(request);
                break;
            case "delete":
                diseaseService.deleteDisease(request);
                break;
        }
    }
}
