package com.inai.hospital.controller;

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
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;


    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Request request) {
        return ResponseEntity.ok(apiService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody Request request) {
        return ResponseEntity.ok(apiService.register(request));
    }
}