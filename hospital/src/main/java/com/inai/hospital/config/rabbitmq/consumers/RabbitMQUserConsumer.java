package com.inai.hospital.config.rabbitmq.consumers;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitMQUserConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQDiseaseConsumer.class);

    private final ApiService apiService;

    private final UserService userService;

    private final RoleService roleService;

    private final PermissionService permissionService;


    @RabbitListener(queues = {"${rabbitmq.user.queue.name}"})
    public void consume(Request request) {
        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | RabbitMQUserConsumer | BODY: {}",
                UUID.randomUUID(), Status.SUCCESS, request);

        switch (request.getUserApi()) {
            case "register_user":
                apiService.register(request);
                break;
            case "update_user":
                userService.updateUser(request);
                break;
            case "delete_user":
                userService.deleteUser(request);
                break;
            case "create_role":
                roleService.createRole(request);
                break;
            case "update_role":
                roleService.updateRole(request);
                break;
            case "delete_role":
                roleService.deleteRole(request);
                break;
            case "create_permission":
                permissionService.createPermission(request);
                break;
            case "update_permission":
                permissionService.updatePermission(request);
                break;
            case "delete_permission":
                permissionService.deletePermission(request);
                break;
            case "add_permission_to_role":
                permissionService.addPermissionToRole(request);
                break;
            case "delete_permission_from_role":
                permissionService.deletePermissionFromRole(request);
                break;
        }
    }
}
