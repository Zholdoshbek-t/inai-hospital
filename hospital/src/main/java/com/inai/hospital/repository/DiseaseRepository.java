package com.inai.hospital.repository;

import com.inai.hospital.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Optional<Disease> findByName(String name);

    @Query(
            value = "SELECT * FROM disease " +
                    "WHERE name LIKE '%:text%'",
            nativeQuery = true
    )
    List<Disease> findAllByText(String text);
}
