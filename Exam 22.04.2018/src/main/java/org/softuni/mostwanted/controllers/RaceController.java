package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dto.RaceEntriesImportXmlWrapper;
import org.softuni.mostwanted.models.dto.RaceEntryImportXMLDto;
import org.softuni.mostwanted.models.dto.RaceImportXMLDto;
import org.softuni.mostwanted.models.dto.RacesImportXmlWrapper;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceController {
    private final Parser xmlParser;
    private final RaceService raceService;

    @Autowired
    public RaceController(@Qualifier("XMLParser") Parser xmlParser,
                               RaceService raceService) {
        this.xmlParser = xmlParser;
        this.raceService = raceService;
    }
    public String importRacesFromXML(String fileContent) {
        RacesImportXmlWrapper wrapper = new RacesImportXmlWrapper();
        try {
            wrapper = xmlParser.read(RacesImportXmlWrapper.class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (RaceImportXMLDto model : wrapper.getRaces()) {
            try {
                Integer id = raceService.create(model);
                sb.append(String.format("Succesfully imported Race – %s.", id));
                sb.append(System.lineSeparator());
            } catch (IllegalStateException e){
                sb.append("Error: Duplicate Data!");
                sb.append(System.lineSeparator());
            }
            catch (Exception e) {
                e.printStackTrace();
                sb.append("Error: Incorrect data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
