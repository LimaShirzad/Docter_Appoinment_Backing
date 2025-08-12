package com.doctoreappointmentProject.doctoreappointmentProject.util;


public class PatientInfoUtil {


    public String validateBloodGroup(String bloodGroup)
    {

        if(bloodGroup==null || bloodGroup.isBlank())
        {

            return  "Blood group should not be empty";

        }

        if(bloodGroup.length() > 5)
        {

            return  "Blood Group Should Be less Than 5 characters";

        }

        return null;
    }



}
