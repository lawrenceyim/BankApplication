package com.solvd.bankapplication.utils.sax;

import com.solvd.bankapplication.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxParser {
    private static final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private static final String employeesXml = "src/main/resources/sax/EmployeesExample.xml";

    public static void parseEmployees() {
        final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        final SaxHandler saxHandler = new SaxHandler();
        try {
            File file = new File(employeesXml);
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(file, saxHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        List<Employee> employees = saxHandler.getEmployees();
        employees.stream().forEach(employee -> {
            OUTPUT_LOGGER.info(employee.toString());
        });
    }
}
