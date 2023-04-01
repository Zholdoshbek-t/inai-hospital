package com.inai.hospital.controller.another;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/government/main")
@RequiredArgsConstructor
public class GovernmentController {

    private final WebClient webClient;

    // different apis

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Request request) {
        Response response = webClient
                .method(HttpMethod.POST)
                .uri("/hospital/api/login")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-disease-id")
    public ResponseEntity<Response> getRegisteredDiseasesByDisease(@RequestBody Request request) {
        Response response = webClient
                .method(HttpMethod.GET)
                .uri("/hospital/diseases-registered/get-by-disease-id")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-patient-id")
    public ResponseEntity<Response> getRegisteredDiseasesByPatient(@RequestBody Request request) {
        Response response = webClient
                .method(HttpMethod.GET)
                .uri("/hospital/diseases-registered/get-by-patient-id")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        return ResponseEntity.ok(response);
    }
}
