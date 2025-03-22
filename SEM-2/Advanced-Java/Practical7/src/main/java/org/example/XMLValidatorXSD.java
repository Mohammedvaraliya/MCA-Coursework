package org.example;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XMLValidatorXSD {

    public static void main(String[] args) {
        try {
            String xmlFile = "src/main/resources/data/students.xml";
            String xsdFile = "src/main/resources/data/students.xsd";

           // Create a schemaFactory and specify XML Schema language
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

           // Load the xml schema from file
            Schema schema = factory.newSchema(new File(xsdFile));

            // Create a validator object for the schema
            Validator validator = schema.newValidator();

            //  Validate the XML object for the schema
            validator.validate(new StreamSource(new File(xmlFile)));

            System.out.println("XML validation successful: XML document is valid.");
        } catch (Exception ex) {
            System.out.println("XML validation failed: " + ex.getMessage());
        }
    }
}