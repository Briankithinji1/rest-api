package com.example.restapi.controller;

import com.example.restapi.model.Student;
import com.example.restapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Student>> find(@RequestBody Student student) {
        return new ResponseEntity<List<Student>>(studentService.fetchAllStudent(), HttpStatus.OK);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Student> findOne(@PathVariable int id) {
        return new ResponseEntity<Student>(studentService.fetchStudent(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.updateStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return new ResponseEntity<String>(studentService.deleteStudent(id), HttpStatus.ACCEPTED);
    }
}
