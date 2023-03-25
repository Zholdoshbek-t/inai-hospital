package com.inai.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    private Long diseaseId;
    private String diseaseName;
    private String newDiseaseName;

    private Long diseaseRegisteredId;
    private String diseaseRegisteredName;
    private Long diseaseRegisteredPatientId;
    private Long diseaseRegisteredDoctorId;
    private LocalDateTime diseaseRegisteredDateTime;
    private String diseaseRegisteredResult;
    private String diseaseRegisteredSymptoms;
    private String diseaseRegisteredComments;

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
    private String newRoleName;

    private Long permissionId;
    private String permissionName;
    private String newPermissionName;
    private String permissionDescription;
    private String newPermissionDescription;
}
