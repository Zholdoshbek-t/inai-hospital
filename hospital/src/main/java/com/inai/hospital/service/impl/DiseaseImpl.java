package com.inai.hospital.service.impl;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.entity.Disease;
import com.inai.hospital.entity.Role;
import com.inai.hospital.mappers.DiseaseMapper;
import com.inai.hospital.mappers.ResponseMapper;
import com.inai.hospital.repository.DiseaseRepository;
import com.inai.hospital.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseImpl implements DiseaseService {

    private final Logger LOGGER = LoggerFactory.getLogger(DiseaseImpl.class);

    private final DiseaseRepository diseaseRepository;


    @Override
    public Response getDiseases() {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Get diseases | BODY: {}",
                uuid, Status.SUCCESS, "getDiseases()");

        List<Response> disease = diseaseRepository.findAll()
                .stream()
                .map(DiseaseMapper::getDiseaseDto)
                .collect(Collectors.toList());

        return ResponseMapper.getResponse(
                "Diseases returned",
                Status.SUCCESS,
                disease
        );
    }

    @Override
    public Response getDiseasesByText(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Get diseases by text | BODY: {}",
                uuid, Status.SUCCESS, "getDiseasesByText()");

        List<Response> disease = diseaseRepository.findAllByText(request.getDiseaseName())
                .stream()
                .map(DiseaseMapper::getDiseaseDto)
                .collect(Collectors.toList());

        return ResponseMapper.getResponse(
                "Diseases by text returned",
                Status.SUCCESS,
                disease
        );
    }

    @Override
    public Response createDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Create disease | BODY: {}",
                uuid, Status.SUCCESS, "createDisease()");

        Optional<Disease> optionalDisease = diseaseRepository.findByName(request.getDiseaseName());

        if (optionalDisease.isPresent()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Create disease | BODY: {}",
                    uuid, Status.ERROR, "Disease already exists with the given name");

            return ResponseMapper.getResponse(
                    "Disease already exists with the given name",
                    Status.ERROR,
                    false
            );
        }

        Disease disease = Disease.builder()
                .name(request.getDiseaseName())
                .build();

        diseaseRepository.save(disease);

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Create disease | BODY: {}",
                uuid, Status.SUCCESS, disease);

        return ResponseMapper.getResponse(
                "Disease was created",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response updateDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Update disease | BODY: {}",
                uuid, Status.SUCCESS, "updateDisease()");

        Optional<Disease> optionalDisease = diseaseRepository.findByName(request.getDiseaseName());

        if (optionalDisease.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update disease | BODY: {}",
                    uuid, Status.ERROR, "Disease was not found with the given name");

            return ResponseMapper.getResponse(
                    "Disease was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        optionalDisease.get().setName(request.getNewDiseaseName());

        diseaseRepository.save(optionalDisease.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update disease | BODY: {}",
                uuid, Status.SUCCESS, optionalDisease.get());

        return ResponseMapper.getResponse(
                "Disease was updated",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response deleteDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Delete disease | BODY: {}",
                uuid, Status.SUCCESS, "deleteDisease()");

        Optional<Disease> optionalDisease = diseaseRepository.findByName(request.getDiseaseName());

        if (optionalDisease.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete disease | BODY: {}",
                    uuid, Status.ERROR, "Disease was not found with the given name");

            return ResponseMapper.getResponse(
                    "Disease was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        diseaseRepository.delete(optionalDisease.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete disease | BODY: {}",
                uuid, Status.SUCCESS, "DELETED");

        return ResponseMapper.getResponse(
                "Disease was deleted",
                Status.SUCCESS,
                true
        );
    }
}
