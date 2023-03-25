package com.inai.hospital.mappers;

import com.inai.hospital.dto.Response;
import com.inai.hospital.dto.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    public static Response getResponse(String message, Status status, Object data) {
        return Response.builder()
                .message(message)
                .status(status)
                .data(data)
                .build();
    }
}
