package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ProcedureController {

    private final Parser xmlParser;
    private final ProcedureService procedureService;

    public ProcedureController(@Qualifier("XMLParser") Parser xmlParser,
                               ProcedureService procedureService) {
        this.xmlParser = xmlParser;
        this.procedureService = procedureService;
    }

    public String importDataFromXML(String xmlContent){

        ProcedureWrapperXMLImportDTO wrapper = null;
        try {
            wrapper = xmlParser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (ProcedureXMLImportDTO model : wrapper.getProcedures()) {
            try {
                procedureService.create(model);
                sb.append("Record successfully imported.");
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        return xmlParser.write(procedureService.exportProcedures());
    }
}
