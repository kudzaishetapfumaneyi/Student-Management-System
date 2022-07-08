package co.zw.startect.utils;

import co.zw.startect.entity.Student;

import java.io.PrintWriter;
import java.util.List;

public class CsvUtils {

    public static void downloadCsv(PrintWriter writer, List<Student> students) {
        writer.write("Student ID, First Name, Last Name,Address, Email, Age, Class, Contacts\n");
        for (Student student : students) {
            writer.write(student.getId() + "," + student.getFirstName() + "," + student.getLastName() +  "," + student.getEmail() + "," + student.getAddress() + "," + student.getAge() + "," + student.getClassF() + "," + student.getContacts() + "\n");
        }
    }
}


