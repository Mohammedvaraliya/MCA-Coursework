package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class CreateXMLFileUniversityCourseCatalogSystem {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            Element universityElement = document.createElement("university");
            universityElement.setAttribute("name", "K.J.Sim");
            document.appendChild(universityElement);

            Element departmentElement = document.createElement("department");
            departmentElement.setAttribute("id", "CS");
            departmentElement.setAttribute("name", "Computer Science");
            universityElement.appendChild(departmentElement);

            Element departmentHeadElement = document.createElement("department_head");
            departmentHeadElement.setAttribute("faculty_id", "F101");
            departmentElement.appendChild(departmentHeadElement);

            Element coursesElement = document.createElement("courses");
            departmentElement.appendChild(coursesElement);

            addCourseElement(document, coursesElement, "CS101", "3");
            addCourseElement(document, coursesElement, "CS102", "4");

            Element facultyElement = document.createElement("faculty");
            universityElement.appendChild(facultyElement);

            addFacultyMemberElement(document, facultyElement, "F101");
            addFacultyMemberElement(document, facultyElement, "F102");
            addFacultyMemberElement(document, facultyElement, "F103");

            File dir = new File("src/main/resources/data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File xmlFile = new File(dir, "university.xml");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(xmlFile);

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);

            System.out.println("XML file created successfully at: " + xmlFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addCourseElement(Document document, Element coursesElement, String courseCode, String credits) {
        Element courseElement = document.createElement("course");
        courseElement.setAttribute("code", courseCode);
        courseElement.setAttribute("credits", credits);
        coursesElement.appendChild(courseElement);
    }

    private static void addFacultyMemberElement(Document document, Element facultyElement, String facultyId) {
        Element facultyMemberElement = document.createElement("faculty_member");
        facultyMemberElement.setAttribute("id", facultyId);
        facultyElement.appendChild(facultyMemberElement);
    }
}