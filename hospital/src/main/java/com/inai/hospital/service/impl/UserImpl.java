package com.inai.hospital.service.impl;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.entity.Role;
import com.inai.hospital.entity.User;
import com.inai.hospital.exceptions.ResourceNotFoundException;
import com.inai.hospital.mappers.ResponseMapper;
import com.inai.hospital.repository.RoleRepository;
import com.inai.hospital.repository.UserRepository;
import com.inai.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserImpl.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    @Override
    public Response updateUser(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Update user | BODY: {}",
                uuid, Status.SUCCESS, "updateUser()");

        User user = getAuthentication();

        if (user == null) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update user | BODY: {}",
                    uuid, Status.ERROR, "User was not found");

            return ResponseMapper.getResponse(
                    "User was not found",
                    Status.ERROR,
                    false
            );
        }

        Optional<Role> optionalRole = roleRepository.findByName(request.getRoleName());

        if (optionalRole.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update user | BODY: {}",
                    uuid, Status.ERROR, "User Role name was not provided");

            return ResponseMapper.getResponse(
                    "User Role name was not provided",
                    Status.ERROR,
                    false
            );
        }


        user.setInn(request.getUserInn());
        user.setPassword(request.getUserPassword());
        user.setFirstName(request.getUserFirstName());
        user.setLastName(request.getUserLastName());
        user.setMiddleName(request.getUserMiddleName());
        user.setAge(request.getUserAge());
        user.setIsFemale(request.getUserIsFemale());
        user.setRole(optionalRole.get());
        user.setActive(true);

        userRepository.save(user);

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update user | BODY: {}",
                uuid, Status.SUCCESS, user);

        return ResponseMapper.getResponse(
                "User was updated",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public Response deleteUser(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Delete user | BODY: {}",
                uuid, Status.SUCCESS, "deleteUser()");

        User user = getAuthentication();

        if (user == null) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Update user | BODY: {}",
                    uuid, Status.ERROR, "User was not found");

            return ResponseMapper.getResponse(
                    "User was not found",
                    Status.ERROR,
                    false
            );
        }

        userRepository.delete(user);

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Delete user | BODY: {}",
                uuid, Status.SUCCESS, "DELETED");

        return ResponseMapper.getResponse(
                "User was deleted",
                Status.SUCCESS,
                true
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByInn(username)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Пользователь не был найден с никнейном - "
                                + username)
                );

        return User.getUserDetails(user);
    }

    public User getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> user = userRepository.findByInn(authentication.getName());

        return user.orElse(null);
    }
}
