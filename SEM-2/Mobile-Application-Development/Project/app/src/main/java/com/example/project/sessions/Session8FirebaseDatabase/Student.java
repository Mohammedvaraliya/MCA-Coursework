package com.example.project.sessions.Session8FirebaseDatabase;

public class Student {
    public String rollno, email, name, password, department;

    public Student() { }

    public Student(String rollno, String email, String name, String password, String department) {
        this.rollno = rollno;
        this.email = email;
        this.name = name;
        this.password = password;
        this.department = department;
    }
}