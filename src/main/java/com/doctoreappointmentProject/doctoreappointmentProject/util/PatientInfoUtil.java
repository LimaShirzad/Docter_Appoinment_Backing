package com.doctoreappointmentProject.doctoreappointmentProject.util;


import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.PatientInfoRepository;

public class PatientInfoUtil {



    public  static void validateBloodGroupOnlyLetter(String BloodGroup) {

            if(!ValidationUtil.isOnlyLetters(BloodGroup)){

                throw new
                        IllegalArgumentException("Blood Group name should contain only letters");

            }




        }

        public static   void validatePatientNotExist(User user, PatientInfoRepository patientInfoRepository){

            if (patientInfoRepository.existsByPatient(user)) {
                throw new IllegalArgumentException("Patient info already exists for this user");
            }


        }




}
