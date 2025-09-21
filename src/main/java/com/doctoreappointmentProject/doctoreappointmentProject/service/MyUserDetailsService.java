package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private  final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));


        String role = user.getRole().getRole().toUpperCase(); // "DOCTOR" or "ADMIN"

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(role)  // Spring will add "ROLE_" prefix automatically
                .build();

//
//        return  org.springframework.security.core.userdetails.User
//                .withUsername(user.getUserName())
//                .password(user.getPassword())
//               .roles(user.getRole().getRole())
//                .build();


//        return new  org.springframework.security.core.userdetails.User(
//                user.getUserName(),
//                user.getPassword(),
//                new ArrayList<>()
//
//        );
//

    }

}
