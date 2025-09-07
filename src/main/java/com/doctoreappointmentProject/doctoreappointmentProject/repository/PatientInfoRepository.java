package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.model.PatientInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInfoRepository extends JpaRepository<PatientInfo,Long> {


    boolean existsByPatient(User user);
}
