package com.inai.hospital.config.rabbitmq.consumers;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.service.DiseaseRegisteredService;
import com.inai.hospital.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitMQDiseaseRegConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQDiseaseConsumer.class);

    private final DiseaseRegisteredService diseaseRegisteredService;

    @RabbitListener(queues = {"${rabbitmq.disease.registered.queue.name}"})
    public void consume(Request request) {
        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | RabbitMQDiseaseRegisteredConsumer | BODY: {}",
                UUID.randomUUID(), Status.SUCCESS, request);

        switch (request.getDiseaseRegisteredApi()) {
            case "register":
                diseaseRegisteredService.registerDisease(request);
                break;
            case "update":
                diseaseRegisteredService.updateRegisteredDisease(request);
                break;
            case "cured":
                diseaseRegisteredService.curedDisease(request);
                break;
            case "lethal":
                diseaseRegisteredService.lethalDisease(request);
                break;
            case "wrong":
                diseaseRegisteredService.wrongDisease(request);
                break;
        }
    }
}
