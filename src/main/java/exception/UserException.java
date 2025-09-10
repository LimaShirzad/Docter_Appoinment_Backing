package exception;

import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserException {


    private static  UserRepository userRepository;
    private static  RoleRepository roleRepository;

    public UserException(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public static   void checkIfEmailExistThrowException(String email){

        if(userRepository.existsByEmail(email)){

            throw  new IllegalArgumentException("This Email is Already Exist");

        }

    }

    public static void checkIPasswordExistThrowException(String password){

        if(userRepository.existsByPassword(password)){

            throw new IllegalArgumentException("This Password is Already Exist");

        }


    }

    public static void checkIfUserNameExistThrowException(String username){

        if(userRepository.existsByUserName(username)){

            throw new IllegalArgumentException("This UserName is Already Exist");

        }

    }





//    public static void isOnlyLetter(String name){
//
//       if(!ValidationUtil.isOnlyLetters(name))  {
//
//           throw new IllegalArgumentException("Should Contain Only Letters");
//
//
//       }
//
//    }












}
