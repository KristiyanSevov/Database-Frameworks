package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnomalyVictimsController {

    private final FileParser jsonParser;
    private final AnomalyService anomalyService;

    @Autowired
    public AnomalyVictimsController(@Qualifier("JSONParser") FileParser jsonParser, AnomalyService anomalyService) {
        this.jsonParser = jsonParser;
        this.anomalyService = anomalyService;
    }

    public String importDataFromJSON(String fileContent){
        AnomalyVictimsJSONDto[] models = null;
        try {
            models = jsonParser.read(AnomalyVictimsJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (AnomalyVictimsJSONDto model : models) {
            try {
                anomalyService.create(model);
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
