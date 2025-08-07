package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

@Table(name="user_role")

@Data

@NoArgsConstructor

@AllArgsConstructor

public class Role {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="role_id")

    private  Long role_id;

    @NotBlank(message = "the role sholud not be blank")

    @Column(name="role" ,nullable = false,unique = true,length = 50)

    private  String role;

    @OneToMany(mappedBy = "role_refrence",cascade = CascadeType.ALL,orphanRemoval = true)

    private List<User> users;

























}
