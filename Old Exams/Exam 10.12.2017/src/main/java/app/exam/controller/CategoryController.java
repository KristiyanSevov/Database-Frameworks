package app.exam.controller;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {
    private final Parser xmlParser;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("XMLParser") Parser xmlParser,
                              CategoryService categoryService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
    }

    public String getCategoriesWithMostPopularItemsSorted(List<String> categoryNames) {
        CategoriesFrequentItemsXMLExportDTO model = categoryService.getCategoriesWithMostPopularItems(categoryNames);
        try {
            return xmlParser.write(model);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
