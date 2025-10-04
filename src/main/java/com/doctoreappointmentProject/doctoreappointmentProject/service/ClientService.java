package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoClientDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.DoctorInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService {

    private  final DoctorInfoRepository doctorInfoRepository;


    public ClientService(DoctorInfoRepository doctorInfoRepository) {
        this.doctorInfoRepository = doctorInfoRepository;
    }

    public List<DoctorInfoClientDTO> getAllDoctor(){

        return doctorInfoRepository.findAllDoctor();

    }





}
