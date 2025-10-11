package com.doctoreappointmentProject.doctoreappointmentProject.service;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.AdminProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoClientDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.mapper.UserMapper;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.DoctorInfoRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.PatientInfoRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashBoardService {

    @Autowired
    private final   DoctorInfoRepository doctorInfoRepository;
    private final   PatientInfoRepository patientInfoRepository;
    private final    UserRepository userRepository;
    private final UserMapper userMapper;

    public DashBoardService(DoctorInfoRepository doctorInfoRepository, PatientInfoRepository patientInfoRepository, UserRepository userRepository, UserMapper userMapper) {

        this.doctorInfoRepository = doctorInfoRepository;
        this.patientInfoRepository = patientInfoRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public long getTotalPatient() {

        return patientInfoRepository.count();

    }

    public long getTotalDoctor() {

        return doctorInfoRepository.count();

    }

    public List<DoctorInfoClientDTO> getAllDoctor(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<DoctorInfo> doctorInfoPage = doctorInfoRepository.findAll(pageable);

        return doctorInfoPage.stream().map(d -> new DoctorInfoClientDTO(
                d.getDoctor().getId(),
                d.getDoctor().getFirstName(),
                d.getDoctor().getLastName(),
                d.getDoctor().getEmail(),
                d.getSpecialty().getTitle(),
                d.getEducation(),
                d.getExperienceYear(),
                d.getUniversityName(),
                d.getAccepted(),
                d.getGraduationYear(),
                d.getAddress(),
                d.getDoctor().getGender(),
                d.getDoctor().getProfilePicture()

        )).collect(Collectors.toList());


    }

    public void deleteDocotorById(Long id) {

        userRepository.deleteById(id);

    }


    public DoctorInfoClientDTO getDoctoById(Long id) {

        User user =userRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor Not Found withe ID :" + id));

        DoctorInfo doctorInfo=doctorInfoRepository.findByDoctorId((long) user.getId()).orElseThrow(()-> new RuntimeException("Doctor Not Found: " + id));


        return  userMapper.getDocoterByIdMapper(user,doctorInfo);


    }

    public AdminProfileDTO getAdminProfile(String username) {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        AdminProfileDTO dto=new AdminProfileDTO();

        return userMapper.getAdminProfile(user);


    }
}