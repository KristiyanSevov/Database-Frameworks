package com.masdefect.controller;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PlanetService;
import com.masdefect.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class PlanetsController {

    private final FileParser jsonParser;
    private final PlanetService planetService;

    @Autowired
    public PlanetsController(@Qualifier("JSONParser") FileParser jsonParser, PlanetService planetService) {
        this.jsonParser = jsonParser;
        this.planetService = planetService;
    }

    public String importDataFromJSON(String fileContent){
        PlanetImportJSONDto[] models = null;
        try {
        models = jsonParser.read(PlanetImportJSONDto[].class, fileContent);
    } catch (IOException | JAXBException e) {
        e.printStackTrace();
    }
    StringBuilder sb = new StringBuilder();
        for (PlanetImportJSONDto model : models) {
        try {
            planetService.create(model);
            sb.append(String.format("Successfully imported Planet %s.", model.getName()));
            sb.append(System.lineSeparator());
        } catch (Exception e) {
            sb.append("Error: Invalid data.");
            sb.append(System.lineSeparator());
        }
    }
        return sb.toString();
    }

    public String planetsWithNoPeopleTeleportedToThem(){
        List<PlanetExportJSONDto> models = planetService.findAllPlanetsWithoutPeopleTeleportedFromThem();
        try {
            return jsonParser.write(models, "I don't need this in the method...");
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
