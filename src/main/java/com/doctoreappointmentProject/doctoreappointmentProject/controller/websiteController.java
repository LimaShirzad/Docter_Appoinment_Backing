package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class websiteController {

    @GetMapping("/header")

    public Map<String,String> WebsiteHeader()
    {

        Map<String,String> websiteLinks=new HashMap<>();

        websiteLinks.put("home","Home");
        websiteLinks.put("login","Login");
        websiteLinks.put("register","Regitration");
        websiteLinks.put("app_name","Docter Appointment");

        return  websiteLinks;


    }





}
