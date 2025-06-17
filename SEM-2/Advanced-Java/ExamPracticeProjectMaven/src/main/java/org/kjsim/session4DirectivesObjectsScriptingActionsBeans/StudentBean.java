package org.kjsim.session4DirectivesObjectsScriptingActionsBeans;

import java.io.Serializable;

public class StudentBean implements Serializable {
    private String rollno;
    private String name;
    private String email;
    private String department;

    public String getRollno() { return rollno; }
    public void setRollno(String rollno) { this.rollno = rollno; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}

