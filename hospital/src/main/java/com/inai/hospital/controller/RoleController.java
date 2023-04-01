package com.inai.hospital.controller;

import com.inai.hospital.config.rabbitmq.producers.RabbitMQUserProducer;
import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RabbitMQUserProducer rabbitMQUserProducer;

    @PostMapping("/create")
    public ResponseEntity<String> createRole(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateRole(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteRole(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }
}
