package com.doctoreappointmentProject.doctoreappointmentProject.config;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {


//    boolean existsById(int user);
//    boolean existsByUserName(String username);
//    boolean existsByEmail(String email);
//    boolean existsByPassword(String password);
//
//    User findByUserName(String username);


    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        Roles role=new Roles();
        role.setId(80);

        return args -> {

            // Create default admin user if not existsByPassword
            if (!userRepository.existsByUserName("admin")) {
                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@gamil.com");
                admin.setFirstName("Administrator");
                admin.setLastName("khan");
                admin.setGender(Gender.FEMALE);
                admin.setRole(role);
//                admin.setEnabled(true);
                userRepository.save(admin);
                System.out.println("Default admin user created: admin / admin123");
            }

            // Create a default editor for testing
//            if (!userRepository.existsByUsername("editor")) {
//                User editor = new User();
//                editor.setUsername("editor");
//                editor.setPassword(passwordEncoder.encode("editor123"));
//                editor.setEmail("editor@agriblog.com");
//                editor.setFullName("Default Editor");
//                editor.setRole(User.Role.EDITOR);
//                editor.setEnabled(true);
//                userRepository.save(editor);
//                System.out.println("Default editor user created: editor / editor123");
//            }
        };
   }
    }

