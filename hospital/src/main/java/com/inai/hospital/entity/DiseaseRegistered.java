package com.inai.hospital.entity;

import com.inai.hospital.dto.enums.DiseaseResult;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`disease_registered`")
public class DiseaseRegistered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symptoms;

    private String comments;

    @Enumerated(EnumType.STRING)
    private DiseaseResult diseaseResult;

    private LocalDateTime registeredAt;

    private LocalDateTime lastUpdatedAt;

    private LocalDateTime curedAt;

    private LocalDateTime lethalAt;

    private LocalDateTime wrongAt;

    @ManyToOne
    @JoinTable(name = "disease_id")
    private Disease disease;

    @ManyToOne
    @JoinTable(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinTable(name = "doctor_id")
    private User doctor;
}
