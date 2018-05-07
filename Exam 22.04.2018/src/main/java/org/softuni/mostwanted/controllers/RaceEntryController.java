package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dto.RaceEntriesImportXmlWrapper;
import org.softuni.mostwanted.models.dto.RaceEntryImportXMLDto;
import org.softuni.mostwanted.models.dto.TownsJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntryController {
    private final Parser xmlParser;
    private final RaceEntryService raceEntryService;

    @Autowired
    public RaceEntryController(@Qualifier("XMLParser") Parser xmlParser,
                               RaceEntryService raceEntryService) {
        this.xmlParser = xmlParser;
        this.raceEntryService = raceEntryService;
    }

    public String importRaceEntriesFromXML(String fileContent) {
        RaceEntriesImportXmlWrapper wrapper = new RaceEntriesImportXmlWrapper();
        try {
            wrapper = xmlParser.read(RaceEntriesImportXmlWrapper.class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (RaceEntryImportXMLDto model : wrapper.getEntries()) {
                try {
                    Integer id = raceEntryService.create(model);
                    sb.append(String.format("Succesfully imported Race Entry – %s.", id));
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
