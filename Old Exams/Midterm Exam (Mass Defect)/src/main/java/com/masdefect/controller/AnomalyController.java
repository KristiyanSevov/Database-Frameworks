package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AnomalyController {
    private final FileParser jsonParser;
    private final FileParser xmlParser;
    private final AnomalyService anomalyService;

    @Autowired
    public AnomalyController(@Qualifier("JSONParser") FileParser jsonParser,
                             @Qualifier("XMLParser")FileParser xmlParser,
                             AnomalyService anomalyService) {
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.anomalyService = anomalyService;
    }

    public String importDataFromJSON(String fileContent) {
        AnomalyImportJSONDto[] models = null;
        try {
            models = jsonParser.read(AnomalyImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (AnomalyImportJSONDto model : models) {
            try {
                anomalyService.create(model);
                sb.append("Successfully imported anomaly.");
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String importDataFromXML(String fileContent) {
        AnomaliesXMLDto wrapper = null;
        try {
            wrapper = xmlParser.read(AnomaliesXMLDto.class, fileContent);
        } catch (IOException | JAXBException ignored) {
        }
        StringBuilder sb = new StringBuilder();
        for (AnomalyXMLDto model : wrapper.getAnomalies()) {
            try {
                anomalyService.create(model);
                sb.append("Successfully imported data.");
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String findAnomalyWithMostVictims() {
        try {
            return jsonParser.write(anomalyService.findMostAffectingAnomalies(), "useless");
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String exportAnomaliesOrdered() {
        try {
            return xmlParser.write(anomalyService.finaAllAnomalies(), "useless");
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
