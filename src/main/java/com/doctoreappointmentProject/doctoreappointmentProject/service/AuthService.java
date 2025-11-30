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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {


    private  final AuthenticationManager authenticationManager;
    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }


    public LoginResponse login(String username, String password) {

        User user = userRepository.findByUserName(username);

        if(user == null) {
            throw new RuntimeException("Invalid Username or Password");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid Username or Password");
        }

        // Generate Token
        String token = JwtUtil.generateToken(
                user.getUserName(),
                (long) user.getId(),
                user.getRole().getRole());

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setId(user.getId());
        loginResponse.setUserName(user.getUserName());
        loginResponse.setRole(user.getRole().getRole());
        loginResponse.setRoleId(user.getRole().getId());
        loginResponse.setToken(token);

        return loginResponse;
    }


//    public LoginResponse login(String username, String password) {
//
//
////
////            Authentication authentication=authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(username,password)
////
////
////            );
//
//
//            User user=userRepository.findByUserName(username);
////                    .orElseThrow(()-> new RuntimeException("User Not Found"));
//        System.out.println("username is " + user.getUserName());
//
//
//            if(user==null)
//            {
//                  throw new RuntimeException("Invalid UserName or Password");
//            }
//
//            String encryptPassword=passwordEncoder.encode(password);
//
//        System.out.println("user password" + user.getPassword());
//        System.out.println("encypt password" + encryptPassword);
////        passwordEncoder.matches()
//
//            if(!passwordEncoder.matches(password,user.getPassword())){
//
//                throw new RuntimeException("Invalid UserName or Password");
//
//            }
//
//
//            String token=JwtUtil.generateToken(user.getUserName(), (long) user.getId(),user.getRole().getRole());
//
//           LoginResponse loginResponse = new LoginResponse();
//
//           loginResponse.setId(user.getId());
//           loginResponse.setUserName(user.getUserName());
//           loginResponse.setRole(user.getRole().getRole());
//           loginResponse.setRoleId(user.getRole().getId());
//           loginResponse.setToken(token);
//
//           return  loginResponse;
//
////            return Map.of("token",token,
////                    "id",user.getId(),
////                    "username",user.getUserName(),
////                    "role",user.getRole().getId(),
////                    "roleName",user.getRole());
//
//
//
//
//
//    }
//


}

