package org.kjsim;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "practical8_student_table")
public class Student {

    @Id
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int age;

    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student {" +
                "\n\tID: " + id +
                "\n\tFirst Name: '" + firstName + '\'' +
                "\n\tLast Name: '" + lastName + '\'' +
                "\n\tAge: " + age +
                "\n\tEmail: '" + email + '\'' +
                "\n}";
    }
}