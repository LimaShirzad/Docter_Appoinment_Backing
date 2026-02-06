package com.doctoreappointmentProject.doctoreappointmentProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class DoctorAppointmentProjectApplication {

	public static void main(String[] args)
	{

		SpringApplication.run(DoctorAppointmentProjectApplication.class, args);

	}

}