package com.inai.hospital.service;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;

public interface ApiService {

    Response login(Request request);

    Response register(Request request);
}
