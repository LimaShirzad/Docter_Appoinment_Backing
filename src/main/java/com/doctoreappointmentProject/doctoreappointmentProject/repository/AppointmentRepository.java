package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Appointment;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {


    Optional<Appointment> findByPatientAndDoctorId(User patient, User doctor);
}
