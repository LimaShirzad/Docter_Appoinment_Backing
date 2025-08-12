package com.doctoreappointmentProject.doctoreappointmentProject.util;



public class RolesUtil {


    public static String validateRole(String role)
    {

           if(role == null || role.isBlank())
           {

               return  "Role Should not be empty";

           }

           if(role.length() > 50)
           {
                  return "Role Should not be greater than 50 characters";

           }

           return  null;

    }





}

