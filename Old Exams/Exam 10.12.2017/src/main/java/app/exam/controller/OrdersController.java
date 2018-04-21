package app.exam.controller;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
public class OrdersController {

    private final Parser xmlParser;
    private final Parser jsonParser;
    private final OrderService orderService;

    @Autowired
    public OrdersController(@Qualifier("XMLParser") Parser xmlParser,
                            @Qualifier("JSONParser") Parser jsonParser,
                            OrderService orderService) {
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
        this.orderService = orderService;
    }

    public String importDataFromXML(String xmlContent) {
        OrderWrapperXMLImportDTO wrapper = new OrderWrapperXMLImportDTO();
        try {
            wrapper = xmlParser.read(OrderWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (OrderXMLImportDTO model : wrapper.getOrders()) {
            try {
                orderService.create(model);
                sb.append(String.format("Order for %s on %s added.", model.getCustomer(),
                        new SimpleDateFormat("dd/MM/yyyy HH:mm").format(model.getDate())));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        EmployeeOrdersJSONExportDTO model = orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType);
        try {
            return jsonParser.write(model);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
