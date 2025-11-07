package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import lombok.Data;

@Data




public class LoginResponse {

//     "id",user.getId(),
//                    "username",user.getUserName(),
//                    "role",user.getRole().getId(),
//                    "roleName",user.getRole());
    private  int id;
    private    String userName;
    private    String role;
    private    int roleId;
    private  String token;




}
