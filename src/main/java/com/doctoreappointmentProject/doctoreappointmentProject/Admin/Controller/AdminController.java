package com.doctoreappointmentProject.doctoreappointmentProject.Admin.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

            @GetMapping("/admin/login")
            public  String showLoginPage()
            {
                   return  "Please login to your account";
            }


            @GetMapping("/admin/dashbord")
            public Map<String,String> showAdminDashbord()
            {


                Map<String,String> data=new HashMap<>();

                data.put("dashbord","Dashbord");

                data.put("docter","Docter");

                data.put("patient","Patient");

                data.put("admindashbord","Admin Dashbord");

                data.put("brand","My Clinc");

                data.put("report","All Report");

                 return data;



            }


    @GetMapping("/admin/patient")
    public  String  patientPage()
    {
             return "pateint call-----";

    }







}
