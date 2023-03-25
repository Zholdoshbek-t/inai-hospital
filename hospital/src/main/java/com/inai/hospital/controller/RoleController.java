package com.inai.hospital.controller;

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

    private final RoleService roleService;


    @PostMapping("/create")
    public ResponseEntity<Response> createRole(@RequestBody Request request) {
        return ResponseEntity.ok(roleService.createRole(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Response> updateRole(@RequestBody Request request) {
        return ResponseEntity.ok(roleService.updateRole(request));
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> deleteRole(@RequestBody Request request) {
        return ResponseEntity.ok(roleService.deleteRole(request));
    }
}
