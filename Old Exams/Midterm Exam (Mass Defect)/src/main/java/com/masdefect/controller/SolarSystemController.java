package com.masdefect.controller;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class SolarSystemController {

    private final SolarSystemService solarSystemService;
    private final FileParser jsonParser;

    @Autowired
    public SolarSystemController(SolarSystemService solarSystemService,
                                 @Qualifier("JSONParser") FileParser jsonParser) {
        this.solarSystemService = solarSystemService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String fileContent) {
        SolarSystemImportJSONDto[] models = null;
        try {
            models = jsonParser.read(SolarSystemImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (SolarSystemImportJSONDto model : models) {
            try {
                solarSystemService.create(model);
                sb.append(String.format("Successfully imported Solar System %s.", model.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
