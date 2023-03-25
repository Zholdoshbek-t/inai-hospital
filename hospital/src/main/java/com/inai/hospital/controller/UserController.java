package com.inai.hospital.controller;

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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody Request request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> deleteUser(@RequestBody Request request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }
}
