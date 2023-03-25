package com.inai.hospital.service.impl;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.entity.Role;
import com.inai.hospital.mappers.ResponseMapper;
import com.inai.hospital.repository.RoleRepository;
import com.inai.hospital.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleImpl implements RoleService {

    private final Logger LOGGER = LoggerFactory.getLogger(RoleImpl.class);

    private final RoleRepository roleRepository;


    @Override
    public Response createRole(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Create role | BODY: {}",
                uuid, Status.SUCCESS, "createRole()");

        Optional<Role> optionalRole = roleRepository.findByName(request.getRoleName());

        if(optionalRole.isPresent()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Create role | BODY: {}",
                    uuid, Status.ERROR, "Role already exists with the given name");

            return ResponseMapper.getResponse(
                    "Role already exists with the given name",
                    Status.ERROR,
                    false
            );
        }

        Role role = Role.builder()
                .name(request.getRoleName())
                .build();

        roleRepository.save(role);

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Create role | BODY: {}",
                uuid, Status.SUCCESS, role);

        return ResponseMapper.getResponse(
                "Role was created",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response updateRole(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Update role | BODY: {}",
                uuid, Status.SUCCESS, "updateRole()");

        Optional<Role> optionalRole = roleRepository.findByName(request.getRoleName());

        if(optionalRole.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update role | BODY: {}",
                    uuid, Status.ERROR, "Role was not found with the given name");

            return ResponseMapper.getResponse(
                    "Role was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        optionalRole.get().setName(request.getNewRoleName());

        roleRepository.save(optionalRole.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update role | BODY: {}",
                uuid, Status.SUCCESS, optionalRole.get());

        return ResponseMapper.getResponse(
                "Role was updated",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response deleteRole(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Delete role | BODY: {}",
                uuid, Status.SUCCESS, "deleteRole()");

        Optional<Role> optionalRole = roleRepository.findByName(request.getRoleName());

        if(optionalRole.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete role | BODY: {}",
                    uuid, Status.ERROR, "Role was not found with the given name");

            return ResponseMapper.getResponse(
                    "Role was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        roleRepository.delete(optionalRole.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete role | BODY: {}",
                uuid, Status.SUCCESS, "DELETED");

        return ResponseMapper.getResponse(
                "Role was deleted",
                Status.SUCCESS,
                true
        );
    }
}
