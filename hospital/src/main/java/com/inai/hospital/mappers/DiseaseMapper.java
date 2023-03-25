package com.inai.hospital.mappers;

import com.inai.hospital.dto.Response;
import com.inai.hospital.entity.Disease;
import org.springframework.stereotype.Component;

@Component
public class DiseaseMapper {

    public static Response getDiseaseDto(Disease disease) {
        return Response.builder()
                .diseaseName(disease.getName())
                .build();
    }
}
