package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dto.TownsExportJSONDto;
import org.softuni.mostwanted.models.dto.TownsJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class TownController {

    private final Parser jsonParser;
    private final TownService townService;

    @Autowired
    public TownController(@Qualifier("JSONParser") Parser jsonParser,
                          TownService townService) {
        this.jsonParser = jsonParser;
        this.townService = townService;
    }

    public String importTownsFromJSON(String fileContent) {
        TownsJSONImportDto[] models = null;
        try {
            models = jsonParser.read(TownsJSONImportDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (TownsJSONImportDto model : models) {
            try {
                townService.create(model);
                sb.append(String.format("Succesfully imported Town – %s.", model.getName()));
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

    public String exportRacingTowns() {
        List<TownsExportJSONDto> models = townService.exportRacingTowns();
        try {
            return jsonParser.write(models);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
