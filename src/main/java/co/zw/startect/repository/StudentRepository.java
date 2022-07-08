package co.zw.startect.repository;

import co.zw.startect.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //Custom query
//    @Query(value = "select * from students s where s.firstName like %:keyword% or where s.lastName like %:keyword%", nativeQuery = true)
//    List<Student> findByKeyword(@Param("keyword") String keyword);
    @Query(value= "select * from students s where s.first_name like %:keyword% or s.last_name like %:keyword%", nativeQuery = true )
    List<Student> findByKeyword(@Param("keyword") String keyword);

    //Student_Management_System_Mastered.students
}
