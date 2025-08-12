package com.doctoreappointmentProject.doctoreappointmentProject.util;


import java.util.regex.Pattern;

public class UserUtil {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[A-Za-z0-9_]{5,100}$");

    // Validate First Name
    public static String validateFirstName(String firstName) {

        if (firstName == null || firstName.isBlank()) {

            return "First name should not be empty";
        }

        if (firstName.length() > 100) {

            return "First name should be less than 100 characters";

        }

        return null;
    }

    // Validate Last Name
    public static String validateLastName(String lastName) {

        if (lastName == null || lastName.isBlank()) {

            return "Last name should not be empty";

        }

        if (lastName.length() > 100) {

            return "Last name should be less than 100 characters";

        }

        return null;

    }

    // Validate Email
    public static String validateEmail(String email) {

        if (email == null || email.isBlank()) {

            return "Email should not be empty";

        }

        if (email.length() > 250) {

            return "Email should be less than 250 characters";

        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {

            return "Invalid email format";

        }

        return null;
    }

    // Validate Username
    public static String validateUsername(String username) {

        if (username == null || username.isBlank()) {

            return "Username should not be empty";

        }

        if (username.length() < 5 || username.length() > 100) {

            return "Username must be between 5 and 100 characters";

        }

        if (!USERNAME_PATTERN.matcher(username).matches()) {

            return "Username can only contain letters, numbers, and underscores";

        }
        return null;
    }

    // Validate Password
    public static String validatePassword(Integer password) {

        if (password == null) {

            return "Password should not be empty";
        }
        // Since password is Integer in your entity (unusual), check its length
        if (password.toString().length() < 6) {

            return "Password must be at least 6 digits";

        }


        if (password.toString().length() >30) {

            return "Password must be greater than 30 digits";

        }


        return null;
    }


}
