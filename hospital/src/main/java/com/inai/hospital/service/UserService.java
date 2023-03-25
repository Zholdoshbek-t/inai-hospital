package com.inai.hospital.service;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Response updateUser(Request request);

    Response deleteUser(Request request);
}
