package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dto.CarsImportJSONDto;
import org.softuni.mostwanted.models.dto.RacersImportJSONDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.CarService;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarController {
    private final Parser jsonParser;
    private final CarService carService;

    @Autowired
    public CarController(@Qualifier("JSONParser") Parser jsonParser,
                           CarService carService) {
        this.jsonParser = jsonParser;
        this.carService = carService;
    }

    public String importCarsFromJSON(String fileContent) {
        CarsImportJSONDto[] models = null;
        try {
            models = jsonParser.read(CarsImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (CarsImportJSONDto model : models) {
            try {
                carService.create(model);
                sb.append(String.format("Succesfully imported Car - %s %s @ %s", model.getBrand(),
                        model.getModel(), model.getYearOfProduction()));
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
