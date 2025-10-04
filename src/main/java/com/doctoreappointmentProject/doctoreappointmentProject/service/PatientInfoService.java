package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.PatientInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.PatientInfoProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.mapper.PateintInfoMapper;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.PatientInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.PatientInfoRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.PatientInfoUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoService {


    private  final PatientInfoRepository patientInfoRepository;

    private  final UserRepository userRepository;

    private final PateintInfoMapper pateintInfoMapper;


    public PatientInfoService(PatientInfoRepository patientInfoRepository, UserRepository userRepository, PateintInfoMapper pateintInfoMapper) {

        this.patientInfoRepository = patientInfoRepository;
        this.userRepository = userRepository;
        this.pateintInfoMapper = pateintInfoMapper;
    }

    @Transactional
    public  void savePatientInfo(PatientInfoDTO patientInfoDTO){


        User user = userRepository.findById(Long.valueOf(patientInfoDTO.getUserId()))
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + patientInfoDTO.getUserId()));

        PatientInfoUtil.validateBloodGroupOnlyLetter(patientInfoDTO.getBloodGroup());

        PatientInfoUtil.validatePatientNotExist(user,patientInfoRepository);


        PatientInfo patientInfo=new PatientInfo();

        patientInfo.setBloodGroup(patientInfoDTO.getBloodGroup());

        patientInfo.setPatient(user);

        patientInfoRepository.save(patientInfo);


    }

    public PatientInfoProfileDTO getPatientProfile(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        PatientInfo patientInfo = patientInfoRepository.findByPatient(user)
                .orElseThrow(() -> new RuntimeException("patient profile not found"));

        return pateintInfoMapper.toDTO(user, patientInfo);
    }





}
