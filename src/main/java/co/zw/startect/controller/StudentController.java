package co.zw.startect.controller;

import co.zw.startect.entity.Student;
import co.zw.startect.repository.StudentRepository;
import co.zw.startect.service.StudentService;
import co.zw.startect.utils.CsvUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    //inject dependency studentService
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public String Home() {
        return "index";
    }

    @GetMapping("/students")
    public String listStudents(Model model, String keyword) {
        model.addAttribute("students", studentService.getAllStudents());


//        if(keyword != null) {
//            model.addAttribute("students", studentService.findByKeyword(keyword));
//        }
//        else {
//            model.addAttribute("students", studentService.getStudents());
//        }
        //model.addAttribute("students", studentService.getStudents());


        return "students";

    }

//    @RequestMapping(path = {"/students","/search"})
//    public String home(Student student, Model model, String keyword) {
//        if(keyword!=null) {
//            List<Student> list = studentService.getByKeyword(keyword);
//            model.addAttribute("list", list);
//        }else {
//            List<Student> list = studentService.getAllShops();
//            model.addAttribute("list", list);}
//        return "students";
//    }

    //Handler to add new student
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        //create student object to hold student from dfata
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    //handler method to handle post method for creat student
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";

    }

    //handler to handle edit button
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    //handler for update
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
        @ModelAttribute("student") Student student,
        Model model) {

        //get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setClassF(student.getClassF());
        existingStudent.setContacts(student.getContacts());

        //save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";

    }

    //delete handler method
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/download/students.csv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        List<Student> students = (List<Student>) studentRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = studentService.export(students);
        //response.setContentType("application/octet-stream");
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }

//    @GetMapping("/download/employee.csv")
//    public void downloadCsv(HttpServletResponse response) throws IOException {
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; file=employee.csv");
//        CsvUtils.downloadCsv(response.getWriter(), createTestData()) ;
//    }
//
//    private List<Employee> createTestData() {
//        List<Employee> data = new ArrayList<>();
//        data.add(new Employee(123, "Buffet","Jimmy"));
//        data.add(new Employee(456, "Cartman","Eric"));
//        data.add(new Employee(789, "Jefferson","George"));
//        return data;
//    }
}
