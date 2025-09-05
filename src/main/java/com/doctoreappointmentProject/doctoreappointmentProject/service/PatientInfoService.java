package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.PatientInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.PatientInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.PatientInfoRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoService {


    private  final PatientInfoRepository patientInfoRepository;

    private  final UserRepository userRepository;

    public PatientInfoService(PatientInfoRepository patientInfoRepository, UserRepository userRepository) {

        this.patientInfoRepository = patientInfoRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public  void savePatientInfo(PatientInfoDTO patientInfoDTO){




        User user = userRepository.findById(Long.valueOf(patientInfoDTO.getUserId()))
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + patientInfoDTO.getUserId()));

        if(!ValidationUtil.isOnlyLetters(patientInfoDTO.getBloodGroup())){

            throw new
                    IllegalArgumentException("Blood Group name should contain only letters");

        }

//        if (userRepository.existsById(user.getId())) {
//            throw new IllegalArgumentException("user already exists");
//        }

        PatientInfo patientInfo=new PatientInfo();

        patientInfo.setBloodGroup(patientInfoDTO.getBloodGroup());

        patientInfo.setPatient(user);

        patientInfoRepository.save(patientInfo);



    }





}
