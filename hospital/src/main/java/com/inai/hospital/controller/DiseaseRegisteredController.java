package com.inai.hospital.controller;

import com.inai.hospital.config.rabbitmq.producers.RabbitMQDiseaseRegProducer;
import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.service.DiseaseRegisteredService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diseases-registered")
@RequiredArgsConstructor
public class DiseaseRegisteredController {

    private final DiseaseRegisteredService diseaseRegisteredService;

    private final RabbitMQDiseaseRegProducer rabbitMQDiseaseRegProducer;

    @GetMapping("/get-by-disease-id")
    public ResponseEntity<Response> getRegisteredDiseasesByDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseRegisteredService.getRegisteredDiseasesByDisease(request));
    }

    @GetMapping("/get-by-patient-id")
    public ResponseEntity<Response> getRegisteredDiseasesByPatient(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseRegisteredService.getRegisteredDiseasesByPatient(request));
    }

    @GetMapping("/get-by-doctor-id")
    public ResponseEntity<Response> getRegisteredDiseasesByDoctor(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseRegisteredService.getRegisteredDiseasesByDoctor(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDisease(@RequestBody Request request) {
        rabbitMQDiseaseRegProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateRegisteredDisease(@RequestBody Request request) {
        rabbitMQDiseaseRegProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/cured")
    public ResponseEntity<String> curedDisease(@RequestBody Request request) {
        rabbitMQDiseaseRegProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/lethal")
    public ResponseEntity<String> lethalDisease(@RequestBody Request request) {
        rabbitMQDiseaseRegProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/wrong")
    public ResponseEntity<String> wrongDisease(@RequestBody Request request) {
        rabbitMQDiseaseRegProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }
}
