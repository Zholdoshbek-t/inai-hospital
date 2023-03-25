package com.inai.hospital.service;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;

public interface DiseaseRegisteredService {

    Response getRegisteredDiseasesByDisease(Request request);

    Response getRegisteredDiseasesByPatient(Request request);

    Response getRegisteredDiseasesByDoctor(Request request);

    Response registerDisease(Request request);

    Response updateRegisteredDisease(Request request);

    Response curedDisease(Request request);

    Response lethalDisease(Request request);

    Response wrongDisease(Request request);
}
