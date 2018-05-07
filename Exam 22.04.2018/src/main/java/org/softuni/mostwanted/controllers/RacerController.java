package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dto.*;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RacerController {
    private final Parser jsonParser;
    private final Parser xmlParser;
    private final RacerService racerService;

    @Autowired
    public RacerController(@Qualifier("JSONParser") Parser jsonParser,
                           @Qualifier("XMLParser") Parser xmlParser,
                           RacerService racerService) {
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.racerService = racerService;
    }

    public String importRacersFromJSON(String fileContent) {
        RacersImportJSONDto[] models = null;
        try {
            models = jsonParser.read(RacersImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (RacersImportJSONDto model : models) {
            try {
                racerService.create(model);
                sb.append(String.format("Succesfully imported Racer – %s.", model.getName()));
                sb.append(System.lineSeparator());
            } catch (IllegalStateException e){
                sb.append("Error: Duplicate Data!");
                sb.append(System.lineSeparator());
            }
            catch (Exception e) {
                sb.append("Error: Incorrect data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportRacersWithCars() {
        List<RacersWithCarsExportJSONDto> models = racerService.getRacersWithCars();
        try {
            return jsonParser.write(models);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String exportMostWantedRacer() {
        RacerExportXMLWrapper model = racerService.getMostWantedRacer();
        try {
            return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + xmlParser.write(model);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
