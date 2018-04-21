package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.ItemsService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ItemsController {

    private final Parser jsonParser;
    private final ItemsService itemsService;

    @Autowired
    public ItemsController(@Qualifier(value = "JSONParser") Parser jsonParser, ItemsService itemsService) {
        this.jsonParser = jsonParser;
        this.itemsService = itemsService;
    }

    public String importDataFromJSON(String jsonContent) {
        ItemJSONImportDTO[] models = null;
        try {
            models = jsonParser.read(ItemJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (ItemJSONImportDTO model : models) {
            try {
                itemsService.create(model);
                sb.append(String.format("Record %s successfully imported.%n", model.getName()));
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
