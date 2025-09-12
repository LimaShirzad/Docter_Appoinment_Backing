package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.service.DashBoardService;
//import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashBoardController {

    @Autowired
    private final DashBoardService dashBoardService;

    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }
//patient
    @GetMapping("/allPatent")
    public  long getTotalPatient(){

        return dashBoardService.getTotalPatient();

    }

    @GetMapping("/allDoctor")
    public long getTotalDoctor(){

        return dashBoardService.getTotalDoctor();

    }

//    @GetMapping("/data")
//    public Map<String,String> getDasbord(Authentication authentication){
//
//        System.out.println(authentication);
//
//        Map<String ,String > response=new HashMap<>();
//
//        if(authentication==null){
//              response.put("error","nO login");
//              return response;
//        }
//
//        response.put("username",authentication.getName());
//
//        return  response;
//
//
//    }







}
