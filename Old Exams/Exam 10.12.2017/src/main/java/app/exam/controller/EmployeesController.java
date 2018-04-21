package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeesController {

    private final Parser jsonParser;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeesController(@Qualifier(value = "JSONParser") Parser jsonParser,
                               EmployeeService employeeService) {
        this.jsonParser = jsonParser;
        this.employeeService = employeeService;
    }

    public String importDataFromJSON(String jsonContent) {
        EmployeeJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(EmployeeJSONImportDTO[].class, jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (EmployeeJSONImportDTO model : models) {
            try{
                employeeService.create(model);
                sb.append(String.format("Record %s successfully imported.%n", model.getName()));
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
