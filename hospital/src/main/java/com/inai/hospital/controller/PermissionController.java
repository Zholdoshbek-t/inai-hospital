package com.inai.hospital.controller;

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

    private final PermissionService permissionService;


    @PostMapping("/create")
    public ResponseEntity<Response> createPermission(@RequestBody Request request) {
        return ResponseEntity.ok(permissionService.createPermission(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Response> updatePermission(@RequestBody Request request) {
        return ResponseEntity.ok(permissionService.updatePermission(request));
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> deletePermission(@RequestBody Request request) {
        return ResponseEntity.ok(permissionService.deletePermission(request));
    }

    @PostMapping("/add-to-role")
    public ResponseEntity<Response> addPermissionToRole(@RequestBody Request request) {
        return ResponseEntity.ok(permissionService.addPermissionToRole(request));
    }

    @PostMapping("/delete-from-role")
    public ResponseEntity<Response> deletePermissionFromRole(@RequestBody Request request) {
        return ResponseEntity.ok(permissionService.deletePermissionFromRole(request));
    }
}
