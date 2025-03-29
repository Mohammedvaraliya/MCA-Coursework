package org.kjsim;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class HibernateDemo {

    public static void main(String[] args) {
        // Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.openSession();

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Choose an operation:");
            System.out.println("1. Insert a new student");
            System.out.println("2. Update an existing student");
            System.out.println("3. Read a student by ID");
            System.out.println("4. Delete a student by ID");
            System.out.println("5. Retrieve all students");

            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Insert a new student
                    Student newStudent = new Student();
                    System.out.print("Enter Student ID: ");
                    newStudent.setId(scanner.nextLong());
                    scanner.nextLine();
                    System.out.print("Enter First Name: ");
                    newStudent.setFirstName(scanner.nextLine());
                    System.out.print("Enter Last Name: ");
                    newStudent.setLastName(scanner.nextLine());
                    System.out.print("Enter Age: ");
                    newStudent.setAge(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Enter Email: ");
                    newStudent.setEmail(scanner.nextLine());

                    session.beginTransaction();
                    session.save(newStudent);
                    session.getTransaction().commit();
                    System.out.println("Student saved successfully!");
                    break;

                case 2:
                    System.out.print("Enter Student ID to update: ");
                    long updateId = scanner.nextLong();
                    scanner.nextLine();
                    Student existingStudent = session.get(Student.class, updateId);

                    if (existingStudent != null) {
                        System.out.println("Updating student: " + existingStudent);
                        System.out.print("Enter new First Name (current: " + existingStudent.getFirstName() + "): ");
                        existingStudent.setFirstName(scanner.nextLine());
                        System.out.print("Enter new Last Name (current: " + existingStudent.getLastName() + "): ");
                        existingStudent.setLastName(scanner.nextLine());
                        System.out.print("Enter new Age (current: " + existingStudent.getAge() + "): ");
                        existingStudent.setAge(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Enter new Email (current: " + existingStudent.getEmail() + "): ");
                        existingStudent.setEmail(scanner.nextLine());

                        session.beginTransaction();
                        session.update(existingStudent);
                        session.getTransaction().commit();
                        System.out.println("Student updated successfully!");
                    } else {
                        System.out.println("Student not found with ID: " + updateId);
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to read: ");
                    long readId = scanner.nextLong();
                    scanner.nextLine();

                    Student student = session.get(Student.class, readId);
                    if (student != null) {
                        System.out.println("Student details: " + student);
                    } else {
                        System.out.println("No student found with ID: " + readId);
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    long deleteId = scanner.nextLong();
                    scanner.nextLine();

                    Student studentToDelete = session.get(Student.class, deleteId);
                    if (studentToDelete != null) {
                        session.beginTransaction();
                        session.delete(studentToDelete);
                        session.getTransaction().commit();
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("No student found with ID: " + deleteId);
                    }
                    break;

                case 5:
                    // Retrieve all students
                    System.out.println("Retrieving all students...");
                    Query<Student> query = session.createQuery("from Student", Student.class);
                    List<Student> students = query.getResultList();

                    if (!students.isEmpty()) {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    } else {
                        System.out.println("No students found.");
                    }
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}