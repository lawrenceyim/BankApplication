package com.solvd.bankapplication.utils.json;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    private static final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");

    public static void main(String[] args) {
        final String fileName = "src/main/resources/json/Locations.json";
        File file = new File(fileName);
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            Locations locations = objectMapper.readValue(file, Locations.class);
            locations.getLocations().stream().forEach(location -> {
                OUTPUT_LOGGER.info(location.toString());
            });
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
