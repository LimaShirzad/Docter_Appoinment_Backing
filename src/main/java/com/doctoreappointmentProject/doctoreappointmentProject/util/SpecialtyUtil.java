package com.doctoreappointmentProject.doctoreappointmentProject.util;

public class SpecialtyUtil {


    public static void isOnlyLetters(String title)
    {

        if(!ValidationUtil.isOnlyLetters(title)){

            throw new
                    IllegalArgumentException("Specialty name should contain only letters");

        }


    }




}
