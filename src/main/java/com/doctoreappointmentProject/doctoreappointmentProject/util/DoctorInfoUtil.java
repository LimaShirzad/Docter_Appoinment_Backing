package com.doctoreappointmentProject.doctoreappointmentProject.util;

import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.stereotype.Component;

@Component
public class DoctorInfoUtil {


    public  static  void cheackIsOnlyLetter(String data){

        if(!ValidationUtil.isOnlyLetters(data)){

            throw new
                    IllegalArgumentException("Please Enter only letter");
        }

    }



    }



