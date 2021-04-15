package com.example.aopdemo.services.impl;

import com.example.aopdemo.exceptions.NotAllowedException;
import com.example.aopdemo.models.Student;
import com.example.aopdemo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Map<String, Object> createStudent(Student student) {
        Map<String, Object> response = new HashMap<>();
        if (student.getAddress() == null || student.getStudentName() == null || student.getContactNo() == null) {
            throw new NotAllowedException("StudentName,StudentAddress,StudentContact Should not be Empty");
        }
        response.put("massage", "student created successfully");
        response.put("data", student);
        response.put("error", null);
        return response;
    }

    @Override
    public Map<String, Object> updateStudent(Student student) {
        Map<String, Object> response = new HashMap<>();
        if (student.getId() == 0) {
            throw new NotAllowedException("Plz provide correct Student If for update student Record");
        }
        response.put("massage", "Student Recorded updated successfully");
        response.put("data", student);
        response.put("error", null);
        return response;
    }

    @Override
    public Map<String, Object> getStudentByID(long studentId) {
        return null;
    }

    @Override
    public Map<String, Object> deleteStudentById(long studentId) {
        return null;
    }
}
