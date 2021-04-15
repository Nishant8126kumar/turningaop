package com.example.aopdemo.controllers;

import com.example.aopdemo.models.Student;
import com.example.aopdemo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        System.out.printf("Request object is =:"+student);
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @GetMapping(value = "/getbyid/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudentByID(@PathVariable("studentId") long studentId) {
        return ResponseEntity.ok(studentService.getStudentByID(studentId));
    }

    @DeleteMapping(value = "/delete/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("studentId") long studentId) {
        return ResponseEntity.ok(studentService.deleteStudentById(studentId));
    }
}
