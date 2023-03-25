package com.inai.hospital.service;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;

public interface DiseaseService {

    Response getDiseases();

    Response getDiseasesByText(Request request);

    Response createDisease(Request request);

    Response updateDisease(Request request);

    Response deleteDisease(Request request);
}
