package com.doctoreappointmentProject.doctoreappointmentProject.service;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.LoginRequest;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.LoginResponse;
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
    private  final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public LoginResponse login(String username, String password) {



            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)


            );


            User user=userRepository.findByUserName(username)
                    .orElseThrow(()-> new RuntimeException("User Not Found"));


            String token=JwtUtil.generateToken(user.getUserName(), (long) user.getId(),user.getRole().getRole());

           LoginResponse loginResponse = new LoginResponse();

           loginResponse.setId(user.getId());
           loginResponse.setUserName(user.getUserName());
           loginResponse.setRole(user.getRole().getRole());
           loginResponse.setRoleId(user.getRole().getId());
           loginResponse.setToken(token);

           return  loginResponse;

//            return Map.of("token",token,
//                    "id",user.getId(),
//                    "username",user.getUserName(),
//                    "role",user.getRole().getId(),
//                    "roleName",user.getRole());





    }
}

