package com.doctoreappointmentProject.doctoreappointmentProject.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class ValidationUtil {


    private  static  final  String NAME_PATTERN="^[a-zA-Z\\s'-]+$";


    public  static  boolean validateDate(LocalDate date)
    {

        if(date==null){
            return false;
        }

        if(date.isBefore(LocalDate.now())){

            return false;
        }

        return  true;

    }

    public static boolean validateTime(LocalTime time) {

        if(time==null){
            return  false;
        }
        return  true;

    }

    public  static  String validateAndClean(String input,String fieldName)
    {


        if(input==null)
        {
                  throw  new IllegalArgumentException(fieldName + " cannot be null");
        }

        if(input.trim().isEmpty()){

            throw new IllegalArgumentException(fieldName + "cannot be blank");

        }



        String trimmed=input.trim();

        if(!trimmed.matches(NAME_PATTERN)){
            throw new IllegalArgumentException(fieldName + " containe invalid characters");

        }

        return  trimmed;


    }


}
