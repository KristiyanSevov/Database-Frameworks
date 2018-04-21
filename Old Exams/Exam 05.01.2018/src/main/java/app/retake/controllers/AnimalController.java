package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalController {

    private final Parser jsonParser;
    private final AnimalService animalService;

    @Autowired
    public AnimalController(@Qualifier("JSONParser") Parser jsonParser, AnimalService animalService) {
        this.jsonParser = jsonParser;
        this.animalService = animalService;
    }

    public String importDataFromJSON(String jsonContent) {
        AnimalJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(AnimalJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (AnimalJSONImportDTO model : models) {
            try {
                animalService.create(model);
                sb.append(String.format("Record %s Passport №: %s successfully imported.", model.getName(), model.getPassport().getSerialNumber()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        try {
            return jsonParser.write(animalService.findByOwnerPhoneNumber(phoneNumber));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
