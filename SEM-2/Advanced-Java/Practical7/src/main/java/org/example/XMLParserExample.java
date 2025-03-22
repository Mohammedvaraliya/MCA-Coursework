package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParserExample {

    public static void main(String[] args) {
        // Parsing
        try {
            String xmlFile = "src/main/resources/data/students.xml";
            File inputFile = new File(xmlFile);

            // Initialize DocumentBuilderFactory and DocumentBuilder
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // Parse the XML file
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element is: " + doc.getDocumentElement().getNodeName());

            // Get all "student" elements
            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("-------------------------");

            // Loop through the "student" elements
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                // Check if the current node is an element node
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element studElement = (Element) nNode;

                    // Get the content of "id", "name", and "grade" elements
                    String id = studElement.getElementsByTagName("id").item(0).getTextContent();
                    String name = studElement.getElementsByTagName("name").item(0).getTextContent();
                    String grade = studElement.getElementsByTagName("grade").item(0).getTextContent();

                    // Print student details
                    System.out.println("Student ID: " + id);
                    System.out.println("Student Name: " + name);
                    System.out.println("Student Grade: " + grade);
                    System.out.println("-------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}