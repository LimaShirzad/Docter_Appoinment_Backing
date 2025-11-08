package com.doctoreappointmentProject.doctoreappointmentProject.config;


import com.doctoreappointmentProject.doctoreappointmentProject.util.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfig {

    private  final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

//    verify the username and password during login
    @Bean
    public  AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService uds) throws  Exception{

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(uds)
                .passwordEncoder(encoder)
                .and()
                .build();

    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception
    {
//        /api/client
        http
                .csrf().disable()
                .cors().and() // allow request from diffrent origin
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/api/user/save").permitAll()
                                .requestMatchers("/auth/**").permitAll()
//                                .requestMatchers("/api/dashboard/**").hasRole("ADMIN")

                                .requestMatchers("/api/user/roles").permitAll()
                                .requestMatchers("/api/client/**").permitAll()
                                .requestMatchers("/api/specialty/all_Specialty").permitAll()
                                .requestMatchers("/api/doctors/save").permitAll()
                                .requestMatchers("/api/doctors/**").permitAll()
                                .requestMatchers("/api/diseases/all").hasRole("PATIENTS")
                                .requestMatchers("/api/diseases/save").hasRole("ADMIN")

                                .requestMatchers("/api/doctors/profile").hasRole("DOCTOR")
                                .requestMatchers("/api/patient/save").permitAll()

                                .requestMatchers("/api/dashboard/**").hasRole("ADMIN")
                                .requestMatchers("/api/roles/**").hasRole("ADMIN")
                                .requestMatchers("/api/specialty/**").hasRole("ADMIN")

                                .anyRequest().authenticated())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



        return  http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }




}
