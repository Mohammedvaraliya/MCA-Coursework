package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class CreateXMLFile {

    public static void main(String[] args) {
        try {
            // Step 1: Create a new XML document
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Step 2: Create root element <students>
            Element rootElement = document.createElement("students");
            document.appendChild(rootElement);

            // Add student entries using the helper method
            addChildElement(document, rootElement, "1", "Mohammed Varaliya", "A");
            addChildElement(document, rootElement, "2", "Vraj Shah", "B");
            addChildElement(document, rootElement, "3", "Jayesh Mal", "A+");

            // Step 4: Write the XML content to a file
            File dir = new File("src/main/resources/data");
            if (!dir.exists()) {
                dir.mkdirs(); // Make sure the directory exists
            }
            File xmlFile = new File(dir, "students.xml");

            // Create the Transformer to write the XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(xmlFile);

            // Set pretty print for output
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("XML file created successfully at: " + xmlFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Updated helper method to add student elements with id, name, and grade
    private static void addChildElement(Document document, Element rootElement, String studentId, String studentName, String studentGrade) {
        Element student = document.createElement("student");

        Element id = document.createElement("id");
        id.setTextContent(studentId);
        student.appendChild(id);

        Element name = document.createElement("name");
        name.setTextContent(studentName);
        student.appendChild(name);

        Element grade = document.createElement("grade");
        grade.setTextContent(studentGrade);
        student.appendChild(grade);

        rootElement.appendChild(student);
    }
}