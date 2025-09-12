package com.doctoreappointmentProject.doctoreappointmentProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class crosConfigration {


    @Bean
    public WebMvcConfigurer corsConfigration()
    {


        return  new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5500","http://localhost:5500")

                        .allowedMethods("*");
            }
        };

    }


}
