package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.PatientInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientInfoRepository extends JpaRepository<PatientInfo,Long> {


    boolean existsByPatient(User user);


    Optional<PatientInfo> findByPatient(User username);

}
