package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.DoctorInfoRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.service.DoctorInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")

public class DoctorInfoController {


    private final DoctorInfoService doctorInfoService;

    private  final UserRepository userRepository;

    private Map<String,String> doctorResponse= new HashMap<>();

    public DoctorInfoController(DoctorInfoService doctorInfoService, UserRepository userRepository) {

        this.doctorInfoService = doctorInfoService;
        this.userRepository = userRepository;

    }

    @GetMapping
    public List<DoctorInfoDTO> getDoctor(){

       return doctorInfoService.getAllDoctors();

    }


        @PostMapping("/save")
    public ResponseEntity<Map<String,String>> createDoctor(@Valid @ModelAttribute DoctorInfoDTO doctorInfoSaveDTO,
                                                           @RequestParam(value = "cv",required = false) MultipartFile cv) throws IOException {



            doctorInfoService.saveDoctorInfo(doctorInfoSaveDTO);

            doctorResponse.put("saveDoctor","Account Created Successfully");

            return ResponseEntity.ok(doctorResponse);




        }

//
//    @GetMapping("/me")
//    public ResponseEntity<?> getDoctorProfile(Authentication authentication) {
//        if (authentication == null) {
//            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
//        }
//
//        String username = authentication.getName();
//        User user = userRepository.findByUserName(username)
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//
//        return ResponseEntity.ok(Map.of(
//                "id", user.getId(),
//                "username", user.getUserName(),
//                "role", user.getRole().getRole()
//        ));
//    }

        @GetMapping("/profile")
        public  ResponseEntity<?> getDoctorProfile(Authentication authentication)
        {
            String username=authentication.getName();

            User user=userRepository.findByUserName(username).orElseThrow(()-> new RuntimeException("Doctor not found"));

            return  ResponseEntity.ok(user);

//            return ResponseEntity.ok(Map.of(
//
//                    "id",user.getId(),
//                    "username",user.getUserName(),
//                    "role",user.getRole().getRole()
//
//
//            ));

//
        }



//@GetMapping("/me")
//public ResponseEntity<?> getDoctorProfile(Authentication authentication) {
//    if (authentication == null) {
//        return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
//    }
//
//    String username = authentication.getName();
//    User user = userRepository.findByUserName(username)
//            .orElseThrow(() -> new RuntimeException("Doctor not found"));
//
//    return ResponseEntity.ok(Map.of(
//            "id", user.getId(),
//            "username", user.getUserName(),
//            "role", user.getRole().getRole()
//    ));
//}










}
