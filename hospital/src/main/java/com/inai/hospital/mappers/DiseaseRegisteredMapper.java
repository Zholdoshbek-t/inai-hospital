package com.inai.hospital.mappers;

import com.inai.hospital.dto.Response;
import com.inai.hospital.entity.DiseaseRegistered;
import org.springframework.stereotype.Component;

@Component
public class DiseaseRegisteredMapper {

    public static Response getRegisteredDiseaseDto(DiseaseRegistered diseaseRegistered) {
        return Response.builder()
                .diseaseRegisteredId(diseaseRegistered.getId())
                .diseaseRegisteredName(diseaseRegistered.getDisease().getName())
                .diseaseRegisteredSymptoms(diseaseRegistered.getSymptoms())
                .diseaseRegisteredComments(diseaseRegistered.getComments())
                .diseaseRegisteredAtDateTime(diseaseRegistered.getRegisteredAt())
                .diseaseLethalAtDateTime(diseaseRegistered.getLethalAt())
                .diseaseWrongAtDateTime(diseaseRegistered.getWrongAt())
                .diseaseRegisteredDoctor(diseaseRegistered.getDoctor().getFirstName() + " "
                        + diseaseRegistered.getDoctor().getLastName())
                .diseaseRegisteredPatient(diseaseRegistered.getPatient().getFirstName() + " "
                        + diseaseRegistered.getPatient().getLastName())
                .diseaseRegisteredResult(diseaseRegistered.getDiseaseResult().name())
                .build();
    }
}
