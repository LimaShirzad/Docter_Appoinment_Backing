//package com.doctoreappointmentProject.doctoreappointmentProject.controller;
//
//
//import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
//import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
//import com.doctoreappointmentProject.doctoreappointmentProject.service.JwtService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final JwtService jwtService;
//
//    private  final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthController(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.jwtService = jwtService;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User loginRequest){
//
//        User user=userRepository.findByUserName(loginRequest.getUserName())
//                .orElseThrow(() -> new RuntimeException("User Not Found"));
//
//        if(!passwordEncoder.matches(loginRequest.getPassword(),loginRequest.getUserName())){
//
//            return ResponseEntity.status(401).body("invalid credainalt");
//
//
//        }
//
//        String token= jwtService.generateToken(user.getUserName(),user.getRole().getRole());
//
//        return  ResponseEntity.ok(token);
//
//    }
//
//
//
//}
