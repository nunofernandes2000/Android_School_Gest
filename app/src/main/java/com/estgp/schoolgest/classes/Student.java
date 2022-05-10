package com.estgp.schoolgest.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student implements Serializable {

    private Integer studentNumber;
    private String name;
    private LocalDate birthDate;
    private String address;
    private Integer phone;
    private Integer mobilePhone;
    private String email;
    private School school;

    public Student() {
    }

    public Student(Integer studentNumber, String name, LocalDate birthDate, String address, Integer phone, Integer mobilePhone, String email, School school) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.school = school;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Integer mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  "Student Number: " + (studentNumber == null ? "Not defined" : studentNumber) + "\n" +
                "Name: " + (name == null ? "Not defined" : name) + "\n" +
                "Birth Date: " + (birthDate == null ? "Not defined" : birthDate.format(formatter)) + "\n" +
                "Address: " + (address == null ? "Not defined" : address) + "\n" +
                "Phone: " + (phone == null ? "Not defined" : phone) + "\n" +
                "MobilePhone: " + (mobilePhone == null ? "Not defined" : mobilePhone) + "\n" +
                "Email: " + (email == null ? "Not defined" : email) + "\n" +
                "School: " + (school == null ? "Not defined" : school) + "\n";
    }
}
