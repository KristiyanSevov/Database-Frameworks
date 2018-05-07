package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dto.DistrictsJSONImportDto;
import org.softuni.mostwanted.models.dto.TownsJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.DistrictService;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class DistrictController {
    private final Parser jsonParser;
    private final DistrictService districtService;

    @Autowired
    public DistrictController(@Qualifier("JSONParser") Parser jsonParser,
                          DistrictService districtService) {
        this.jsonParser = jsonParser;
        this.districtService = districtService;
    }
    public String importDistrictsFromJSON(String fileContent) {
        DistrictsJSONImportDto[] models = null;
        try {
            models = jsonParser.read(DistrictsJSONImportDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (DistrictsJSONImportDto model : models) {
            try {
                districtService.create(model);
                sb.append(String.format("Succesfully imported District – %s.", model.getName()));
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
}
