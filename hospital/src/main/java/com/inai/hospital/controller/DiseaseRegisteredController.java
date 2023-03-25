package com.inai.hospital.controller;

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
    public ResponseEntity<Response> registerDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseRegisteredService.registerDisease(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Response> updateRegisteredDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseRegisteredService.updateRegisteredDisease(request));
    }

    @PostMapping("/cured")
    public ResponseEntity<Response> curedDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseRegisteredService.curedDisease(request));
    }

    @PostMapping("/lethal")
    public ResponseEntity<Response> lethalDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseRegisteredService.lethalDisease(request));
    }

    @PostMapping("/wrong")
    public ResponseEntity<Response> wrongDisease(@RequestBody Request request) {
        return ResponseEntity.ok(diseaseRegisteredService.wrongDisease(request));
    }
}
