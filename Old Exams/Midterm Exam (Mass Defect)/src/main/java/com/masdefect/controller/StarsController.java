package com.masdefect.controller;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class StarsController {

    private final FileParser jsonParser;
    private final StarService starService;

    @Autowired
    public StarsController(@Qualifier("JSONParser") FileParser jsonParser, StarService starService) {
        this.jsonParser = jsonParser;
        this.starService = starService;
    }

    public String importDataFromJSON(String fileContent){
        StarImportJSONDto[] models = null;
        try {
            models = jsonParser.read(StarImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (StarImportJSONDto model : models) {
            try {
                starService.create(model);
                sb.append(String.format("Successfully imported Star %s.", model.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
}
}
