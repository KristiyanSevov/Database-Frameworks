package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalAidController {

    private final Parser jsonParser;
    private final AnimalAidService animalAidService;

    @Autowired
    public AnimalAidController(@Qualifier("JSONParser") Parser jsonParser, AnimalAidService animalAidService) {
        this.jsonParser = jsonParser;
        this.animalAidService = animalAidService;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        AnimalAidJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (AnimalAidJSONImportDTO model : models) {
            try {
                animalAidService.create(model);
                sb.append(String.format("Record %s successfully imported.", model.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
