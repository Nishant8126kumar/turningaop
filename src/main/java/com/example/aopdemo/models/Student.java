package com.example.aopdemo.models;

public class Student {

    private long id;
    private String studentName;
    private String courseName;
    private String adharno;
    private String address;
    private String contactNo;

    public Student() {
    }

    public Student(long id, String studentName, String courseName, String adharno, String address, String contactNo) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.adharno = adharno;
        this.address = address;
        this.contactNo = contactNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAdharno() {
        return adharno;
    }

    public void setAdharno(String adharno) {
        this.adharno = adharno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", adharno='" + adharno + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
