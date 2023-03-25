package com.inai.hospital.service.impl;

import com.inai.hospital.dto.Request;
import com.inai.hospital.dto.Response;
import com.inai.hospital.dto.enums.Status;
import com.inai.hospital.entity.Role;
import com.inai.hospital.entity.User;
import com.inai.hospital.jwt.JwtProvider;
import com.inai.hospital.mappers.ResponseMapper;
import com.inai.hospital.repository.RoleRepository;
import com.inai.hospital.repository.UserRepository;
import com.inai.hospital.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiImpl implements ApiService {

    private final Logger LOGGER = LoggerFactory.getLogger(ApiImpl.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtProvider jwtProvider;


    @Override
    public Response login(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Login | BODY: {}",
                uuid, Status.SUCCESS, "login()");

        Optional<User> user = userRepository.findByInn(request.getUserInn());

        if (user.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Login | BODY: {}",
                    uuid, Status.ERROR, "User already exists with the given INN");

            return ResponseMapper.getResponse(
                    "User already exists with the given INN",
                    Status.ERROR,
                    false
            );
        }

        if(bCryptPasswordEncoder.matches(request.getUserPassword(), user.get().getPassword())) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Login | BODY: {}",
                    uuid, Status.SUCCESS, "Successful authentication");

            return Response.builder()
                    .message("Successful authentication")
                    .status(Status.SUCCESS)
                    .userId(user.get().getId())
                    .accessToken(jwtProvider.generateAccessToken(user.get()))
                    .build();
        }

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Login | BODY: {}",
                uuid, Status.ERROR, "Incorrect password");

        return ResponseMapper.getResponse(
                "Incorrect password",
                Status.ERROR,
                false
        );
    }

    @Override
    public Response register(Request request) {
        String uuid = UUID.randomUUID().toString();

        LOGGER.info("REQUEST --> UUID: {} | STATUS: {} | Register user | BODY: {}",
                uuid, Status.SUCCESS, "createUser()");

        Optional<User> user = userRepository.findByInn(request.getUserInn());

        if (user.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register user | BODY: {}",
                    uuid, Status.ERROR, "User already exists with the given INN");

            return ResponseMapper.getResponse(
                    "User already exists with the given INN",
                    Status.ERROR,
                    false
            );
        }

        Optional<Role> optionalRole = roleRepository.findByName(request.getRoleName());

        if (optionalRole.isEmpty()) {
            LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register user | BODY: {}",
                    uuid, Status.ERROR, "User Role name was not provided");

            return ResponseMapper.getResponse(
                    "User Role name was not provided",
                    Status.ERROR,
                    false
            );
        }

        User addUser = User.builder()
                .inn(request.getUserInn())
                .password(bCryptPasswordEncoder.encode(request.getUserPassword()))
                .firstName(request.getUserFirstName())
                .lastName(request.getUserLastName())
                .middleName(request.getUserMiddleName())
                .age(request.getUserAge())
                .isFemale(request.getUserIsFemale())
                .role(optionalRole.get())
                .active(true)
                .build();

        userRepository.save(addUser);

        LOGGER.info("RESPONSE -> UUID: {} | STATUS: {} | Register user | BODY: {}",
                uuid, Status.SUCCESS, addUser);

        return ResponseMapper.getResponse(
                "User was registered",
                Status.SUCCESS,
                true
        );
    }
}
