package com.solvd.bankapplication.utils.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;

public class JaxbParser {
    private static final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private static final String employeeLoginDetailXml = "src/main/resources/jaxb/EmployeeLoginDetailExample.xml";

    public static void parseEmployeeLoginDetail() {
        final File file = new File(employeeLoginDetailXml);
        try {
            JAXBContext context = JAXBContext.newInstance(EmployeeLoginDetails.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            EmployeeLoginDetails employeeLoginDetails = (EmployeeLoginDetails) unmarshaller.unmarshal(file);
            employeeLoginDetails.getEmployeeLoginDetails().stream().forEach(employeeLoginDetail -> {
                OUTPUT_LOGGER.info(employeeLoginDetail.toString());
            });
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}


