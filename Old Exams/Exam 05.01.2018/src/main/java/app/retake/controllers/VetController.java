package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class VetController {

    private final Parser xmlParser;
    private final VetService vetService;

    public VetController(@Qualifier("XMLParser") Parser xmlParser, VetService vetService) {
        this.xmlParser = xmlParser;
        this.vetService = vetService;
    }

    public String importDataFromXML(String xmlContent){
        VetWrapperXMLImportDTO wrapper = null;
        try {
            wrapper = xmlParser.read(VetWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (VetXMLImportDTO model : wrapper.getVets()) {
            try {
                vetService.create(model);
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
