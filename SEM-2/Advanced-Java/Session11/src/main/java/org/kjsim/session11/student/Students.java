package org.kjsim.session11.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Students {

    private List<StudentPOJO> studentPOJOS;

    public Students() {
        studentPOJOS = new ArrayList<>();

        studentPOJOS.add(new StudentPOJO(1L, "Mohammed Varaliya", "m.varaliya@somaiya.edu", "MCA"));
        studentPOJOS.add(new StudentPOJO(2L, "Jayesh Mal", "jayesh.mal@somaiya.edu", "MCA"));
        studentPOJOS.add(new StudentPOJO(3L, "Shreyash Patekar", "shreyash.patekar@somaiya.edu", "MBA"));
    }

    public List<StudentPOJO> getStudents() {
        return studentPOJOS;
    }

    public Optional<StudentPOJO> getStudentById(Long id) {
        return studentPOJOS.stream()
                .filter(studentPOJO -> studentPOJO.getId().equals(id))
                .findFirst();
    }
}
