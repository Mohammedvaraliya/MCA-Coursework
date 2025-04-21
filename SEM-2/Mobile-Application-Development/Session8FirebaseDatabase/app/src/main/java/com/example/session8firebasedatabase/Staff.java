package com.example.session8firebasedatabase;

public class Staff {
    public String staffId, email, name, password, department;

    public Staff() {}

    public Staff(String staffId, String email, String name, String password, String department) {
        this.staffId = staffId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.department = department;
    }
}
