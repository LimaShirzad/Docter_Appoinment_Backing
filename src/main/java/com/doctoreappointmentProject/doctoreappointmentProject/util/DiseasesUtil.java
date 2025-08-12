package com.doctoreappointmentProject.doctoreappointmentProject.util;

public class DiseasesUtil {


    public static  String validateTitle(String title)
    {

        if(title.length() > 100){

            return  "Title Should Not be Greater Than 100 characters";

        }

        return  null;

    }

    public static  String validateDiseaseType(String diseaseType )
    {

        if(diseaseType.length() > 150){

            return  "diseaseType Should Not be Greater Than 150 characters";

        }

        return  null;

    }




}