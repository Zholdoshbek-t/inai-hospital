package com.inai.hospital.controller.hospital;

import com.inai.hospital.config.rabbitmq.producers.RabbitMQDiseaseProducer;
import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital/disease")
@RequiredArgsConstructor
public class DiseaseController {

    private final DiseaseService diseaseService;

    private final RabbitMQDiseaseProducer rabbitMQDiseaseProducer;

    @GetMapping("/get")
    public ResponseEntity<Response> getDiseases() {
        return ResponseEntity.ok(diseaseService.getDiseases());
    }

    @GetMapping("/get-by-text")
    public ResponseEntity<Response> getDiseasesByText(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseService.getDiseasesByText(request));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDisease(@RequestBody Request request) {
        rabbitMQDiseaseProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateDisease(@RequestBody Request request) {
        rabbitMQDiseaseProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteDisease(@RequestBody Request request) {
        rabbitMQDiseaseProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }
}
