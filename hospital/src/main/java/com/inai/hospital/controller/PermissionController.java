package com.inai.hospital.controller;

import com.inai.hospital.config.rabbitmq.producers.RabbitMQUserProducer;
import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final RabbitMQUserProducer rabbitMQUserProducer;

    @PostMapping("/create")
    public ResponseEntity<String> createPermission(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updatePermission(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deletePermission(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/add-to-role")
    public ResponseEntity<String> addPermissionToRole(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/delete-from-role")
    public ResponseEntity<String> deletePermissionFromRole(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }
}
