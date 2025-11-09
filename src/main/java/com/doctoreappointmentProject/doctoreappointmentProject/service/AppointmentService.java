package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.AppointmentSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Appointment;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Diseases;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.AppointmentRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.DiseasesRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DiseasesRepository diseasesRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              UserRepository userRepository,
                              DiseasesRepository diseasesRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.diseasesRepository = diseasesRepository;
    }

    public void takeAppointment(AppointmentSaveDTO dto) {


        User patient = userRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        User doctor = userRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Diseases disease = diseasesRepository.findById(dto.getDiseaseId())
                .orElseThrow(() -> new RuntimeException("Disease not found"));

        boolean exists=appointmentRepository.findByPatientAndDoctorId(patient,doctor).isPresent();

        if(exists){

            throw new RuntimeException("You Already have an appointment withe this doctor");

        }



        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctorId(doctor);
        appointment.setDisease(disease);

        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());

        appointmentRepository.save(appointment);
    }
}
