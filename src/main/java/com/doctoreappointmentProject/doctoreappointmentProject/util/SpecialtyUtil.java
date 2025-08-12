package com.doctoreappointmentProject.doctoreappointmentProject.util;

public class SpecialtyUtil {


    public static String validateTitle(String title)
    {

        if(title==null || title.isBlank())
        {

               return  "Title should not be empty";

        }

        if(title.length() > 150)
        {

            return  "title should not be greater than 150 characters";

        }

        return  null;

    }




}
