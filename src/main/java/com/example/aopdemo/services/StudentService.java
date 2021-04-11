package com.example.aopdemo.services;

import com.example.aopdemo.models.Student;

import java.util.Map;

public interface StudentService {

    Map<String, Object> createStudent(Student student);

    Map<String, Object> updateStudent(Student student);

    Map<String, Object>     getStudentByID(long studentId);

    Map<String, Object> deleteStudentById(long studentId);
}
