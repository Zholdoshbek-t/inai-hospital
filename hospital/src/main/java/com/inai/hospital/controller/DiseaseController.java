package com.inai.hospital.controller;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disease")
@RequiredArgsConstructor
public class DiseaseController {

    private final DiseaseService diseaseService;


    @GetMapping("/get")
    public ResponseEntity<Response> getDiseases() {
        return ResponseEntity.ok(diseaseService.getDiseases());
    }

    @GetMapping("/get-by-text")
    public ResponseEntity<Response>  getDiseasesByText(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseService.getDiseasesByText(request));
    }

    @PostMapping("/create")
    public ResponseEntity<Response>  createDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseService.createDisease(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Response>  updateDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseService.updateDisease(request));
    }

    @PostMapping("/delete")
    public ResponseEntity<Response>  deleteDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseService.deleteDisease(request));
    }
}
