package co.zw.startect.service;

import co.zw.startect.entity.Student;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public interface StudentService {
    //define a method getAll student
    List<Student> getAllStudents();

    //Create save student method
    Student saveStudent(Student student);

    //add the edit method service
    Student getStudentById(Long id);

    Student updateStudent(Student student);

    //define delete method
    void deleteStudent(Long id);

    ByteArrayInputStream export(List<Student> students);

    //Get employees by keyword
    List<Student> getByKeyword(String keyword);

//    List<Student> getAllShops();

    List <Student> getStudents();


    List<Student> findByKeyword(String keyword);
}
