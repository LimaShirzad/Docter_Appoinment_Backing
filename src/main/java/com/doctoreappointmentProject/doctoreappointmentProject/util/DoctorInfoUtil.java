package com.doctoreappointmentProject.doctoreappointmentProject.util;

import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

public class DoctorInfoUtil {


    public  static String  validateEducation(String education) {


        if (education == null || education.isBlank()) {

            return "Education Name Should Not be Empty";

        }

        if (education.length() > 255) {

            return "Education Must Not be Greater Than 255 characters";

        }

              return null;
      }


        @org.jetbrains.annotations.Nullable
        @org.jetbrains.annotations.Contract(value = "null -> !null", pure = true)
        public static String validateExperienceYear(String experienceYear)
        {

            if (experienceYear == null || experienceYear.isBlank()) {

                return "Experience Year Should Not be Empty";

            }

            return  null;

        }

        public  static  String validateUniversityName(String universityName)
        {

            if(universityName==null || universityName.isBlank()){

                return  "universityName Should Not Be Empty";

            }

            if(universityName.length() > 250){

                return  "universityName Should Not Greater Than 250 characters";

            }

            return  null;

        }

        public static String validateGraduationYear(String graduationYear)
        {

            if(graduationYear==null || graduationYear.isBlank()){

                return  "GraduationYear Should Not be Empty";
            }

            return  null;
        }

        public  static  String validateAddress(String address)
        {

            if(address.length() > 500)
            {

                return "User Address Should Not be Greater Than 500 characters";

            }

            return  address;

        }


        public  static  String validateCv(String cv)
        {

            if(cv.length()>300){

                return  "CV link Should Not be Greater Than 300 characters";

            }

            return  null;

        }



    }



