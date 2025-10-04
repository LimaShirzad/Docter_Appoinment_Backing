package com.doctoreappointmentProject.doctoreappointmentProject.mapper;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.PatientInfoProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.PatientInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import org.springframework.stereotype.Component;

@Component
public class PateintInfoMapper {

    public PatientInfoProfileDTO toDTO(User user, PatientInfo patientInfo) {
        if (user == null || patientInfo == null) return null;

        PatientInfoProfileDTO dto = new PatientInfoProfileDTO();
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setUserName(patientInfo.getPatient().getUserName());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setGender(user.getGender());
        dto.setBloodGroup(patientInfo.getBloodGroup());

        return dto;

    }


}
