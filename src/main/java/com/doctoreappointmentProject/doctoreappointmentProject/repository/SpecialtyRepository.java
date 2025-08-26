package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty,Long> {

    boolean existsByTitle(String title);
    boolean existsById(Integer id);


}
