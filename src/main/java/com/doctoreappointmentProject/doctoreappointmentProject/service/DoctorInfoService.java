package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.mapper.DcotorInfoMapper;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.DoctorInfoRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.SpecialtyRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.DoctorInfoUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DoctorInfoService {

     private final DoctorInfoRepository doctorInfoRepository;
     private  final UserRepository userRepository;
     private  final SpecialtyRepository specialtyRepository;
     private  final DcotorInfoMapper doctorInfoMapper;


    public DoctorInfoService(DoctorInfoRepository doctorRepository, UserRepository userRepository, SpecialtyRepository specialtyRepository, DcotorInfoMapper doctorInfoMapper) {

        this.doctorInfoRepository = doctorRepository;
        this.userRepository = userRepository;
        this.specialtyRepository = specialtyRepository;
        this.doctorInfoMapper = doctorInfoMapper;
    }



    public List<DoctorInfoSaveDTO> getAllDoctors() {

        List<DoctorInfo> doctors = doctorInfoRepository.findAll();
        return doctorInfoMapper.toDtoList(doctors);

    }

    @Transactional
    public  void saveDoctorInfo(DoctorInfoSaveDTO doctorInfoSaveDTO) throws IOException {


        DoctorInfo doctorInfo=  doctorInfoMapper.toEntity(doctorInfoSaveDTO);


        DoctorInfoUtil.cheackIsOnlyLetter(doctorInfo.getUniversityName());
        DoctorInfoUtil.cheackIsOnlyLetter(doctorInfo.getEducation());


        User user = userRepository.findById(Long.valueOf(doctorInfoSaveDTO.getUserId()))
                .orElseThrow(() -> new RuntimeException("No User Found"));

        Specialty specialty=specialtyRepository.findById(Long.valueOf(doctorInfoSaveDTO.getSpecialtyId()))
                        .orElseThrow(()->new RuntimeException("No Specialty Found"));



          doctorInfo.setDoctor(user);

          doctorInfo.setSpecialty(specialty);


          doctorInfoRepository.save(doctorInfo);

    }


    public DoctorProfileDTO getDoctorProfile(String username) {
        User user = userRepository.findByUserName(username);
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorInfo doctorInfo = doctorInfoRepository.findByDoctor(user)
                .orElseThrow(() -> new RuntimeException("Doctor profile not found"));

        return doctorInfoMapper.toDTO(user, doctorInfo);
    }

}
