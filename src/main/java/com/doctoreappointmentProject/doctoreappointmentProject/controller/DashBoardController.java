package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.AdminProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoClientDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.service.ClientService;
import com.doctoreappointmentProject.doctoreappointmentProject.service.DashBoardService;
//import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashBoardController {

    @Autowired
    private final DashBoardService dashBoardService;

    private final ClientService clientService;

    private final Map<String,String> dashboardResponse=new HashMap<>();


    public DashBoardController(DashBoardService dashBoardService, ClientService clientService) {
        this.dashBoardService = dashBoardService;
        this.clientService = clientService;
    }



    @GetMapping("/adminprofile")
    public ResponseEntity<?> getAdminProfile(Authentication authentication){

        String username=authentication.getName();
        AdminProfileDTO adminProfile=dashBoardService.getAdminProfile(username);
        return ResponseEntity.ok(adminProfile);

    }

    @GetMapping("/allPatent")
    public  long getTotalPatient(){

        return dashBoardService.getTotalPatient();

    }

    @GetMapping("/allDoctor")
    public long getTotalDoctor(){

        return dashBoardService.getTotalDoctor();

    }

    @GetMapping("/doctors")
    public Map<String,Object> getAllDoctors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){

        List<DoctorInfoClientDTO> doctors = dashBoardService.getAllDoctor(page,size);
        long totalDoctors = dashBoardService.getTotalDoctor();
//        int totalPages = (int) Math.ceil((double) totalDoctors / size);

        int totalPages= Math.toIntExact(totalDoctors / size);

        Map<String,Object> response = new HashMap<>();
        response.put("doctors", doctors);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);
        return response;
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public ResponseEntity<Map<String,String>> deleteDoctorById(@PathVariable Long id){

        dashBoardService.deleteDocotorById(id);

        dashboardResponse.put("deleteMessage","Doctor Remove Successfully");

        return new ResponseEntity<>(dashboardResponse, HttpStatus.OK);

    }

    @GetMapping("/getDocotor/{id}")
    public DoctorInfoClientDTO getDoctorById(@PathVariable Long id){

        return  dashBoardService.getDoctoById(id);

    }





}
