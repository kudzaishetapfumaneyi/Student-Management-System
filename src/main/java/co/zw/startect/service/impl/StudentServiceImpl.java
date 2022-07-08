package co.zw.startect.service.impl;

import co.zw.startect.entity.Student;
import co.zw.startect.repository.StudentRepository;
import co.zw.startect.service.StudentService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    //inject the dependency of student dependency
    @Autowired
    private StudentRepository studentRepository;

    //creat a constructor


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    public List<Student> getByKeyword(String keyword){
        return studentRepository.findByKeyword(keyword);
    }

//    @Override
//    public List<Student> getAll() {
//        return null;
//    }

    public List<Student> findByKeyword(String keyword) {
        return studentRepository.findByKeyword(keyword);
    }
    @Override
    public List<Student> getStudents() {
        return null;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public ByteArrayInputStream export(List<Student> students){
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("students");

            Row row = sheet.createRow(0);

            //Define header cell stlye
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //creating header cells
            Cell cell = row.createCell(0);
            cell.setCellValue("First Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Last Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Email");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Address");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Age");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Class");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("Contacts");
            cell.setCellStyle(headerCellStyle);

            //Creating data rows for each student
            for(int i=0; i< students.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(students.get(i).getFirstName());
                dataRow.createCell(1).setCellValue(students.get(i).getLastName());
                dataRow.createCell(2).setCellValue(students.get(i).getEmail());
                dataRow.createCell(3).setCellValue(students.get(i).getAddress());
                dataRow.createCell(4).setCellValue(students.get(i).getAge());
                dataRow.createCell(5).setCellValue(students.get(i).getClassF());
                dataRow.createCell(6).setCellValue(students.get(i).getContacts());
            }

            //making size of the column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);



            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            logger.error("Error during exporting Excel file", ex);
            return null;
        }

    }
}
