package com.inai.hospital.service.impl;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.dto.enums.DiseaseResult;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.entity.Disease;
import com.inai.hospital.entity.DiseaseRegistered;
import com.inai.hospital.entity.User;
import com.inai.hospital.mappers.DiseaseRegisteredMapper;
import com.inai.hospital.mappers.ResponseMapper;
import com.inai.hospital.repository.DiseaseRegisteredRepository;
import com.inai.hospital.repository.DiseaseRepository;
import com.inai.hospital.repository.UserRepository;
import com.inai.hospital.service.DiseaseRegisteredService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseRegisteredImpl implements DiseaseRegisteredService {

    private final Logger LOGGER = LoggerFactory.getLogger(DiseaseRegisteredImpl.class);

    private final DiseaseRegisteredRepository diseaseRegisteredRepository;

    private final DiseaseRepository diseaseRepository;

    private final UserRepository userRepository;


    @Override
    public Response getRegisteredDiseasesByDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Get Registered Diseases by disease name | BODY: {}",
                uuid, Status.SUCCESS, "getRegisteredDiseasesByDisease()");

        Optional<Disease> disease = diseaseRepository.findById(request.getDiseaseId());

        if (disease.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register Disease | BODY: {}",
                    uuid, Status.ERROR, "Disease was not found with the given name");

            return ResponseMapper.getResponse(
                    "Disease was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        List<Response> diseases =
                diseaseRegisteredRepository.findAllByDiseaseRegisteredDiseaseId(disease.get().getId())
                        .stream()
                        .map(DiseaseRegisteredMapper::getRegisteredDiseaseDto)
                        .collect(Collectors.toList());

        return ResponseMapper.getResponse(
                "Registered Diseases returned by disease id",
                Status.SUCCESS,
                diseases
        );
    }

    @Override
    public Response getRegisteredDiseasesByPatient(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Get Registered Diseases by patient id | BODY: {}",
                uuid, Status.SUCCESS, "getRegisteredDiseasesByPatient()");

        Optional<User> patient = userRepository.findById(request.getDiseaseRegisteredPatientId());

        if (patient.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Get Registered Diseases by patient id | BODY: {}",
                    uuid, Status.ERROR, "Patient was not found");

            return ResponseMapper.getResponse(
                    "Patient was not found",
                    Status.ERROR,
                    false
            );
        }

        List<Response> diseases =
                diseaseRegisteredRepository.findAllByDiseaseRegisteredPatientId(patient.get().getId())
                        .stream()
                        .map(DiseaseRegisteredMapper::getRegisteredDiseaseDto)
                        .collect(Collectors.toList());

        return ResponseMapper.getResponse(
                "Registered Diseases returned by patient id",
                Status.SUCCESS,
                diseases
        );
    }

    @Override
    public Response getRegisteredDiseasesByDoctor(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Get Registered Diseases by doctor id | BODY: {}",
                uuid, Status.SUCCESS, "getRegisteredDiseasesByDoctor()");

        Optional<User> doctor = userRepository.findById(request.getDiseaseRegisteredDoctorId());

        if (doctor.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Get Registered Diseases by doctor id | BODY: {}",
                    uuid, Status.ERROR, "Doctor was not found");

            return ResponseMapper.getResponse(
                    "Doctor was not found",
                    Status.ERROR,
                    false
            );
        }

        List<Response> diseases =
                diseaseRegisteredRepository.findAllByDiseaseRegisteredDoctorId(doctor.get().getId())
                        .stream()
                        .map(DiseaseRegisteredMapper::getRegisteredDiseaseDto)
                        .collect(Collectors.toList());

        return ResponseMapper.getResponse(
                "Registered Diseases returned by doctor id",
                Status.SUCCESS,
                diseases
        );
    }

    @Override
    public Response registerDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Register Disease | BODY: {}",
                uuid, Status.SUCCESS, "registerDisease()");

        Optional<Disease> disease = diseaseRepository.findById(request.getDiseaseId());

        if (disease.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register Disease | BODY: {}",
                    uuid, Status.ERROR, "Disease was not found with the given name");

            return ResponseMapper.getResponse(
                    "Disease was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        Optional<User> doctor = userRepository.findById(request.getDiseaseRegisteredDoctorId());

        if (doctor.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register Disease | BODY: {}",
                    uuid, Status.ERROR, "Doctor was not found");

            return ResponseMapper.getResponse(
                    "Doctor was not found",
                    Status.ERROR,
                    false
            );
        }

        if (!doctor.get().getRole().getName().equals("DOCTOR")) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register Disease | BODY: {}",
                    uuid, Status.ERROR, "Role does not match");

            return ResponseMapper.getResponse(
                    "Role does not match",
                    Status.ERROR,
                    false
            );
        }

        Optional<User> patient = userRepository.findById(request.getDiseaseRegisteredPatientId());

        if (patient.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register Disease | BODY: {}",
                    uuid, Status.ERROR, "Patient was not found");

            return ResponseMapper.getResponse(
                    "Patient was not found",
                    Status.ERROR,
                    false
            );
        }

        if (!patient.get().getRole().getName().equals("PATIENT")) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register Disease | BODY: {}",
                    uuid, Status.ERROR, "Role does not match");

            return ResponseMapper.getResponse(
                    "Role does not match",
                    Status.ERROR,
                    false
            );
        }

        DiseaseRegistered diseaseRegistered = DiseaseRegistered.builder()
                .symptoms(request.getDiseaseRegisteredSymptoms())
                .comments(request.getDiseaseRegisteredComments())
                .diseaseResult(DiseaseResult.UNKNOWN)
                .disease(disease.get())
                .patient(patient.get())
                .doctor(doctor.get())
                .registeredAt(LocalDateTime.now())
                .build();

        diseaseRegisteredRepository.save(diseaseRegistered);

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register disease | BODY: {}",
                uuid, Status.SUCCESS, diseaseRegistered);

        return ResponseMapper.getResponse(
                "Disease was registered",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response updateRegisteredDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Updated Registered Disease | BODY: {}",
                uuid, Status.SUCCESS, "registerDisease()");

        Optional<DiseaseRegistered> optionalDiseaseRegistered =
                diseaseRegisteredRepository.findById(request.getDiseaseRegisteredId());

        if (optionalDiseaseRegistered.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Updated Registered Disease | BODY: {}",
                    uuid, Status.ERROR, "Registered Disease was not found with the given id");

            return ResponseMapper.getResponse(
                    "Registered Disease was not found with the given id",
                    Status.ERROR,
                    false
            );
        }

        Optional<Disease> disease = diseaseRepository.findByName(request.getDiseaseName());

        if (disease.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Updated Registered Disease | BODY: {}",
                    uuid, Status.ERROR, "Disease was not found with the given name");

            return ResponseMapper.getResponse(
                    "Disease was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        Optional<User> doctor = userRepository.findById(request.getDiseaseRegisteredDoctorId());

        if (doctor.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Updated Registered Disease | BODY: {}",
                    uuid, Status.ERROR, "Doctor was not found");

            return ResponseMapper.getResponse(
                    "Doctor was not found",
                    Status.ERROR,
                    false
            );
        }

        if (!doctor.get().getRole().getName().equals("DOCTOR")) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Updated Registered Disease | BODY: {}",
                    uuid, Status.ERROR, "Role does not match");

            return ResponseMapper.getResponse(
                    "Role does not match",
                    Status.ERROR,
                    false
            );
        }

        Optional<User> patient = userRepository.findById(request.getDiseaseRegisteredPatientId());

        if (patient.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Updated Registered Disease | BODY: {}",
                    uuid, Status.ERROR, "Patient was not found");

            return ResponseMapper.getResponse(
                    "Patient was not found",
                    Status.ERROR,
                    false
            );
        }

        if (!doctor.get().getRole().getName().equals("PATIENT")) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Updated Registered Disease | BODY: {}",
                    uuid, Status.ERROR, "Role does not match");

            return ResponseMapper.getResponse(
                    "Role does not match",
                    Status.ERROR,
                    false
            );
        }

        DiseaseRegistered diseaseRegistered = optionalDiseaseRegistered.get();

        diseaseRegistered.setSymptoms(request.getDiseaseRegisteredSymptoms());
        diseaseRegistered.setComments(request.getDiseaseRegisteredComments());
        diseaseRegistered.setDisease(disease.get());
        diseaseRegistered.setPatient(patient.get());
        diseaseRegistered.setDoctor(doctor.get());
        diseaseRegistered.setLastUpdatedAt(LocalDateTime.now());

        diseaseRegisteredRepository.save(diseaseRegistered);

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Updated Registered disease | BODY: {}",
                uuid, Status.SUCCESS, diseaseRegistered);

        return ResponseMapper.getResponse(
                "Registered Disease was updated",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response curedDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Cured Disease | BODY: {}",
                uuid, Status.SUCCESS, "curedDisease()");

        Optional<DiseaseRegistered> optionalDiseaseRegistered =
                diseaseRegisteredRepository.findById(request.getDiseaseRegisteredId());

        if (optionalDiseaseRegistered.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Cured Disease | BODY: {}",
                    uuid, Status.ERROR, "Registered Disease was not found with the given id");

            return ResponseMapper.getResponse(
                    "Registered Disease was not found with the given id",
                    Status.ERROR,
                    false
            );
        }

        optionalDiseaseRegistered.get().setDiseaseResult(DiseaseResult.CURED);
        optionalDiseaseRegistered.get().setComments(request.getDiseaseRegisteredComments());
        optionalDiseaseRegistered.get().setCuredAt(LocalDateTime.now());

        diseaseRegisteredRepository.save(optionalDiseaseRegistered.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Cured disease | BODY: {}",
                uuid, Status.SUCCESS, optionalDiseaseRegistered.get());

        return ResponseMapper.getResponse(
                "Registered Disease was cured",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response lethalDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Lethal Disease | BODY: {}",
                uuid, Status.SUCCESS, "lethalDisease()");

        Optional<DiseaseRegistered> optionalDiseaseRegistered =
                diseaseRegisteredRepository.findById(request.getDiseaseRegisteredId());

        if (optionalDiseaseRegistered.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Lethal Disease | BODY: {}",
                    uuid, Status.ERROR, "Registered Disease was not found with the given id");

            return ResponseMapper.getResponse(
                    "Registered Disease was not found with the given id",
                    Status.ERROR,
                    false
            );
        }

        optionalDiseaseRegistered.get().setDiseaseResult(DiseaseResult.LETHAL);
        optionalDiseaseRegistered.get().setComments(request.getDiseaseRegisteredComments());
        optionalDiseaseRegistered.get().setLethalAt(LocalDateTime.now());

        diseaseRegisteredRepository.save(optionalDiseaseRegistered.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Lethal disease | BODY: {}",
                uuid, Status.SUCCESS, optionalDiseaseRegistered.get());

        return ResponseMapper.getResponse(
                "Registered Disease was lethal",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response wrongDisease(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Wrong Disease | BODY: {}",
                uuid, Status.SUCCESS, "wrongDisease()");

        Optional<DiseaseRegistered> optionalDiseaseRegistered =
                diseaseRegisteredRepository.findById(request.getDiseaseRegisteredId());

        if (optionalDiseaseRegistered.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Wrong Disease | BODY: {}",
                    uuid, Status.ERROR, "Registered Disease was not found with the given id");

            return ResponseMapper.getResponse(
                    "Registered Disease was not found with the given id",
                    Status.ERROR,
                    false
            );
        }

        optionalDiseaseRegistered.get().setDiseaseResult(DiseaseResult.WRONG);
        optionalDiseaseRegistered.get().setComments(request.getDiseaseRegisteredComments());
        optionalDiseaseRegistered.get().setWrongAt(LocalDateTime.now());

        diseaseRegisteredRepository.save(optionalDiseaseRegistered.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Wrong disease | BODY: {}",
                uuid, Status.SUCCESS, optionalDiseaseRegistered.get());

        return ResponseMapper.getResponse(
                "Registered Disease was wrong",
                Status.SUCCESS,
                true
        );
    }
}
