package org.kjsim;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "practical9_employee_table")
public class Employee {

    public Employee() {
        // Default constructor required by Hibernate
    }

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String hireDate;

    @Column
    private String department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "\n\tID: " + id +
                "\n\tName: '" + name + '\'' +
                "\n\tEmail: '" + email + '\'' +
                "\n\tHire Date: '" + hireDate + '\'' +
                "\n\tDepartment: '" + department + '\'' +
                "\n}";
    }
}