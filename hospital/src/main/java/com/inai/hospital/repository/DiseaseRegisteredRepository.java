package com.inai.hospital.repository;

import com.inai.hospital.entity.DiseaseRegistered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRegisteredRepository extends JpaRepository<DiseaseRegistered, Long> {

    @Query(
            value = "SELECT * FROM disease_registered " +
                    "WHERE disease_id = :id",
            nativeQuery = true
    )
    List<DiseaseRegistered> findAllByDiseaseRegisteredDiseaseId(Long id);

    @Query(
            value = "SELECT * FROM disease_registered " +
                    "WHERE patient_id = :id",
            nativeQuery = true
    )
    List<DiseaseRegistered> findAllByDiseaseRegisteredPatientId(Long id);

    @Query(
            value = "SELECT * FROM disease_registered " +
                    "WHERE doctor_id = :id",
            nativeQuery = true
    )
    List<DiseaseRegistered> findAllByDiseaseRegisteredDoctorId(Long id);
}
