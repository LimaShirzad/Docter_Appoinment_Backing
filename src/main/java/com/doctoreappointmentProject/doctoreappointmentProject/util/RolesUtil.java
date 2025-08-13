package com.doctoreappointmentProject.doctoreappointmentProject.util;


import io.netty.util.internal.StringUtil;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class RolesUtil {


    public static String validateRole(String role)
    {

           if(role.length() > 50)
           {
                  return "Role Should not be greater than 50 characters";

           }

           return  null;

    }





}

