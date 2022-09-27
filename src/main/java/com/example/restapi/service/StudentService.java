package com.example.restapi.service;

import com.example.restapi.exception.StudentNotFound;
import com.example.restapi.model.Student;
import com.example.restapi.repository.StudentRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {

        if (student.getEmail() == null){

        }
        return studentRepository.save(student);
    }

    public List<Student> fetchAllStudent() {
        return studentRepository.findAll();
    }

    public Student fetchStudent(int id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFound("Student with id not found" +id));
    }

    public Student updateStudent(@NonNull Student student) {
        Student selectedStudent = fetchStudent(student.getId());

        if (selectedStudent != null && selectedStudent.getName() != null && selectedStudent.getEmail() != null) {
            selectedStudent.setName(student.getName());
            selectedStudent.setEmail(student.getEmail());
            studentRepository.save(selectedStudent);
        }
        return student;
    }

    public String deleteStudent(@NotNull int id) {
        studentRepository.deleteById(id);
        return "Student Deleted";
    }
}
