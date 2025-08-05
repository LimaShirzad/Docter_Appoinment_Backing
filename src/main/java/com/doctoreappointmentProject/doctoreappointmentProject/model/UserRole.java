package com.doctoreappointmentProject.doctoreappointmentProject.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
        import lombok.*;


@Data               // Generates getters, setters, toString, equals, and hashCode

@NoArgsConstructor  // No-arg constructor

@AllArgsConstructor // All-args constructor

@Entity

@Table(name = "user_role")

public class UserRole{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "role_id")
        private Integer roleId;

        @Column(name = "role", nullable = false, unique = true)
        private String role;



}

