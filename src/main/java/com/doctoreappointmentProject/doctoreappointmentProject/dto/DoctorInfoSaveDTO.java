package com.doctoreappointmentProject.doctoreappointmentProject.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfoSaveDTO {

    private int id;

    @Size(max=255,message = "education must be less than 255")
    @NotBlank(message = "must not be empty")
    private String education;

    @NotNull(message = "Experience Year Should Not Be Null")
    private Integer experienceYear;


    @Size(max=250,message = "universityName must less than 250")
    @NotBlank(message = "universityName should not be blank")
    private String universityName;


    @NotNull(message = "the Data filed must not be blank")
    private LocalDate graduationYear;

    @Size(max=500,message = "address should not be greater than 500")
    @NotBlank(message = "address should not be blank")
    private String address;

    @NotNull(message = "Cv Must Not Be Blank")
    private MultipartFile cv;

    private String accepted;


    private Integer userId;

    private int specialtyId;



}

