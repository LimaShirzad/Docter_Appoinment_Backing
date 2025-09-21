package com.doctoreappointmentProject.doctoreappointmentProject.service;


import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {


    private  final AuthenticationManager authenticationManager;

    private final HttpSession session;

//    private  final JwtUtil jwtUtil;

    private  final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, HttpSession session, UserRepository userRepository) {

        this.authenticationManager = authenticationManager;
        this.session = session;
//        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public Map<String, Object> login(String username, String password) {

//        try{

            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)


            );



            User user=userRepository.findByUserName(username)
                    .orElseThrow(()-> new RuntimeException("User Not Found"));



            String token=JwtUtil.generateToken(user.getUserName(), (long) user.getId(),user.getRole().getRole());



            return Map.of("token",token,
                    "id",user.getId(),
                    "username",user.getUserName(),
                    "role",user.getRole().getId(),
                    "roleName",user.getRole());



//
//        }catch (BadCredentialsException e){
//
//            throw  new RuntimeException("invalid UserName or Password");
//        }


    }
}












