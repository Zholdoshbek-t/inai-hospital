package com.inai.hospital.controller.hospital;

import com.inai.hospital.config.rabbitmq.producers.RabbitMQUserProducer;
import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital/users")
@RequiredArgsConstructor
public class UserController {

    private final RabbitMQUserProducer rabbitMQUserProducer;

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }
}
