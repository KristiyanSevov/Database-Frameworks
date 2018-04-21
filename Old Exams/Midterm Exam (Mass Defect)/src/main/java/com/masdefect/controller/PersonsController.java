package com.masdefect.controller;

import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PersonService;
import com.masdefect.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class PersonsController {

    private final FileParser jsonParser;
    private final PersonService personService;

    @Autowired
    public PersonsController(@Qualifier("JSONParser") FileParser jsonParser, PersonService personService) {
        this.jsonParser = jsonParser;
        this.personService = personService;
    }
    public String importDataFromJSON(String fileContent){
        PersonImportJSONDto[] models = null;
        try {
            models = jsonParser.read(PersonImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (PersonImportJSONDto model : models) {
            try {
                personService.create(model);
                sb.append(String.format("Successfully imported Person %s.", model.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
