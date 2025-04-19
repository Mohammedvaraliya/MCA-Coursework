package org.kjsim.session11.student;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    private final Students students;

    public StudentServices() {
        this.students = new Students();
    }

    public List<StudentPOJO> getAllStudents() {
        return students.getStudents();
    }

    public StudentPOJO getStudentById(Long id) {
        Optional<StudentPOJO> student = students.getStudentById(id);
        return student.orElse(null); // Return null if student not found
    }
}
