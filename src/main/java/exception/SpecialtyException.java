package exception;

import com.doctoreappointmentProject.doctoreappointmentProject.repository.SpecialtyRepository;
import org.springframework.stereotype.Component;

@Component
public class SpecialtyException {

    private static  SpecialtyRepository specialtyRepository;

    public SpecialtyException(SpecialtyRepository specialtyRepository) {

        this.specialtyRepository=specialtyRepository;

    }

    public static void checkIfSpecialtyIsAlreadyExist(String specialty){

        if(specialtyRepository.existsByTitle(specialty)){

            throw  new IllegalArgumentException("This Specialty is already Exists");

        }

    }


}
