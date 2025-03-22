package org.example;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLValidatorDTD extends DefaultHandler {

    private String xmlFile = "src/main/resources/data/students.xml";

    public static void main(String[] args) {
        try {
            XMLValidatorDTD validator = new XMLValidatorDTD();
            validator.validate(validator.xmlFile);
            System.out.println("XML validation finished.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void validate(String xmlFile) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);

        SAXParser saxParser = factory.newSAXParser();

        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(this);
        xmlReader.setErrorHandler(this);

        xmlReader.parse(new InputSource(xmlFile));
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("Error: " + e.getMessage());
        throw new SAXException("Error Encountered during validation");
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Fatal Error: " + e.getMessage());
        throw new SAXException("Fatal Error Encountered during validation");
    }
}