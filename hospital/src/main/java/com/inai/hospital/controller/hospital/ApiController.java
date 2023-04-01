package com.inai.hospital.controller.hospital;

import com.inai.hospital.config.rabbitmq.producers.RabbitMQUserProducer;
import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    private final RabbitMQUserProducer rabbitMQUserProducer;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Request request) {
        return ResponseEntity.ok(apiService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Request request) {
        rabbitMQUserProducer.sendMessage(request);

        return ResponseEntity.ok("Запрос был успешно отправлен. Ожидайте.");
    }
}
