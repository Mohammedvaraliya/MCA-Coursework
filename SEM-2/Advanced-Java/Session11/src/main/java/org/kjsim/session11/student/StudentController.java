package org.kjsim.session11.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StudentController {

    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping("/")
    public String test() {
        return "<h1>Hello World</h1>";
    }

    @GetMapping("/students")
    public List<StudentPOJO> getAllStudents() {
        return studentServices.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public StudentPOJO getStudentById(@PathVariable Long id) {
        return studentServices.getStudentById(id);
    }
}