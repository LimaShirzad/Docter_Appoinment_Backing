package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpecialtyRepository extends JpaRepository<Specialty,Long> {

    boolean existsByTitle(String title);
    boolean existsById(Integer id);

//    @Query("""
//  SELECT s FROM Specialty s WHERE CAST(s.id As String) LIKE %keyword% OR LOWER(s.title) LIKE LOWER(CONCAT('%', :keyword,'%')) """)
//    Page<Specialty> searchAllFields(@Param("keyword") String keyword, Pageable pageable);

    @Query("""
    SELECT s FROM Specialty s 
    WHERE CAST(s.id AS string) LIKE %:keyword% 
    OR LOWER(s.title) LIKE LOWER(CONCAT('%', :keyword,'%'))
""")
    Page<Specialty> searchAllFields(@Param("keyword") String keyword, Pageable pageable);

}
