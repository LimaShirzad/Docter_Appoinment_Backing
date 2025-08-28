package com.doctoreappointmentProject.doctoreappointmentProject.service;


import com.doctoreappointmentProject.doctoreappointmentProject.repository.DoctorInfoRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardService {

    @Autowired
    DoctorInfoRepository doctorInfoRepository;
    PatientInfoRepository patientInfoRepository;

    public DashBoardService(DoctorInfoRepository doctorInfoRepository, PatientInfoRepository patientInfoRepository) {

        this.doctorInfoRepository = doctorInfoRepository;
        this.patientInfoRepository = patientInfoRepository;

    }

    public  long getTotalPatient(){

        return  patientInfoRepository.count();

    }

    public long getTotalDoctor(){

        return doctorInfoRepository.count();

    }






}
