package com.inai.hospital.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inai.hospital.dto.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String message;
    private Status status;
    private Object data;

    private String accessToken;

    private Long diseaseId;
    private String diseaseName;

    private Long diseaseRegisteredId;
    private String diseaseRegisteredName;
    private String diseaseRegisteredPatient;
    private String diseaseRegisteredDoctor;
    private LocalDateTime diseaseRegisteredAtDateTime;
    private LocalDateTime diseaseLethalAtDateTime;
    private LocalDateTime diseaseWrongAtDateTime;
    private String diseaseRegisteredResult;
    private String diseaseRegisteredSymptoms;
    private String diseaseRegisteredComments;

    private Long userId;
    private String userInn;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userMiddleName;
    private Integer userAge;
    private Boolean userIsFemale;
    private Long userRoleId;

    private Long roleId;
    private String roleName;

    private Long permissionId;
    private String permissionName;
    private String permissionDescription;
}
