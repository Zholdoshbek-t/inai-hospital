package com.inai.hospital.service.impl;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.entity.Permission;
import com.inai.hospital.entity.Role;
import com.inai.hospital.mappers.ResponseMapper;
import com.inai.hospital.repository.PermissionRepository;
import com.inai.hospital.repository.RoleRepository;
import com.inai.hospital.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionImpl implements PermissionService {

    private final Logger LOGGER = LoggerFactory.getLogger(PermissionImpl.class);

    private final PermissionRepository permissionRepository;

    private final RoleRepository roleRepository;


    @Override
    public Response createPermission(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Create permission | BODY: {}",
                uuid, Status.SUCCESS, "createPermission()");

        Optional<Permission> optionalPermission =
                permissionRepository.findByName(request.getPermissionName());

        if (optionalPermission.isPresent()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Create permission | BODY: {}",
                    uuid, Status.ERROR, "Permission already exists with the given name");

            return ResponseMapper.getResponse(
                    "Permission already exists with the given name",
                    Status.ERROR,
                    false
            );
        }

        Permission permission = Permission.builder()
                .name(request.getPermissionName())
                .explanation(request.getPermissionDescription())
                .build();

        permissionRepository.save(permission);

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Create permission | BODY: {}",
                uuid, Status.SUCCESS, permission);

        return ResponseMapper.getResponse(
                "Permission was created",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response updatePermission(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Update permission | BODY: {}",
                uuid, Status.SUCCESS, "updatePermission()");

        Optional<Permission> optionalPermission =
                permissionRepository.findByName(request.getPermissionName());

        if(optionalPermission.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update permission | BODY: {}",
                    uuid, Status.ERROR, "Permission was not found with the given name");

            return ResponseMapper.getResponse(
                    "Permission was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        optionalPermission.get().setName(request.getNewPermissionName());
        optionalPermission.get().setExplanation(request.getNewPermissionDescription());

        permissionRepository.save(optionalPermission.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update permission | BODY: {}",
                uuid, Status.SUCCESS, optionalPermission.get());

        return ResponseMapper.getResponse(
                "Permission was updated",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response deletePermission(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Delete permission | BODY: {}",
                uuid, Status.SUCCESS, "deletePermission()");

        Optional<Permission> optionalPermission =
                permissionRepository.findByName(request.getPermissionName());

        if(optionalPermission.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete permission | BODY: {}",
                    uuid, Status.ERROR, "Permission was not found with the given name");

            return ResponseMapper.getResponse(
                    "Permission was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        permissionRepository.delete(optionalPermission.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete permission | BODY: {}",
                uuid, Status.SUCCESS, "DELETED");

        return ResponseMapper.getResponse(
                "Permission was deleted",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response addPermissionToRole(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Add permission to role | BODY: {}",
                uuid, Status.SUCCESS, "addPermissionToRole()");

        Optional<Permission> optionalPermission =
                permissionRepository.findByName(request.getPermissionName());

        if(optionalPermission.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Add permission to role | BODY: {}",
                    uuid, Status.ERROR, "Permission was not found with the given name");

            return ResponseMapper.getResponse(
                    "Permission was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        Optional<Role> optionalRole = roleRepository.findByName(request.getRoleName());

        if(optionalRole.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Add permission to role | BODY: {}",
                    uuid, Status.ERROR, "Role was not found with the given name");

            return ResponseMapper.getResponse(
                    "Role was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        optionalRole.get().getPermissions().add(optionalPermission.get());

        roleRepository.save(optionalRole.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Add permission to role | BODY: {}",
                uuid, Status.SUCCESS, optionalRole.get() + " " + optionalPermission.get());

        return ResponseMapper.getResponse(
                "Permission was added to role",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response deletePermissionFromRole(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Delete permission from role | BODY: {}",
                uuid, Status.SUCCESS, "deletePermissionFromRole()");

        Optional<Permission> optionalPermission =
                permissionRepository.findByName(request.getPermissionName());

        if(optionalPermission.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete permission from role | BODY: {}",
                    uuid, Status.ERROR, "Permission was not found with the given name");

            return ResponseMapper.getResponse(
                    "Permission was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        Optional<Role> optionalRole = roleRepository.findByName(request.getRoleName());

        if(optionalRole.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete permission from role | BODY: {}",
                    uuid, Status.ERROR, "Role was not found with the given name");

            return ResponseMapper.getResponse(
                    "Role was not found with the given name",
                    Status.ERROR,
                    false
            );
        }

        optionalRole.get().getPermissions().remove(optionalPermission.get());

        roleRepository.save(optionalRole.get());

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete permission from role | BODY: {}",
                uuid, Status.SUCCESS, "DELETED");

        return ResponseMapper.getResponse(
                "Permission was deleted from role",
                Status.SUCCESS,
                true
        );
    }
}
