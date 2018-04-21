package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderItemRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Validator validator;
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemsRepository itemsRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(Validator validator,
                            OrderRepository orderRepository,
                            EmployeeRepository employeeRepository,
                            ItemsRepository itemsRepository,
                            OrderItemRepository orderItemRepository) {
        this.validator = validator;
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemsRepository = itemsRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        if (validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        Employee employee = employeeRepository.findByName(dto.getEmployee());
        if (employee == null) {
            throw new IllegalArgumentException();
        }
        OrderType type = null;
        if (dto.getType() == null) { //what if empty string?
            type = OrderType.valueOf("ForHere"); //for the default value
        } else {
            type = OrderType.valueOf(dto.getType());
        }//will throw exception if incorrect value
        for (OrderItemXMLImportDTO model : dto.getItems()) {
            if (validator.validate(model).size() != 0) {
                throw new IllegalArgumentException();
            }
            Item item = itemsRepository.findByName(model.getName());
            if (item == null) {
                throw new IllegalArgumentException();
            }
        }

        Order order = new Order(dto.getCustomer(), dto.getDate(), type, employee);
        Set<OrderItem> orderItems = dto.getItems()
                .stream()
                .map(i -> new OrderItem(order, itemsRepository.findByName(i.getName()), i.getQuantity()))
                .collect(Collectors.toSet());
        orderRepository.save(order);
        orderItemRepository.save(orderItems);
    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        List<OrderJSONExportDTO> orders = employeeRepository.findByName(employeeName).getOrders()
                .stream()
                .filter(o -> o.getType().toString().equals(orderType))
                .map(o -> new OrderJSONExportDTO(o.getCustomer(),
                        o.getOrderItems()
                                .stream()
                                .map(oi -> new ItemJSONExportDTO(oi.getItem().getId(), oi.getItem().getName(),
                                        oi.getItem().getPrice(), oi.getQuantity()))
                                .sorted(Comparator.comparing(ItemJSONExportDTO::getId))
                                .collect(Collectors.toList())))
                .sorted(Comparator.comparing((OrderJSONExportDTO o) -> -o.getItems()
                        .stream()
                        .mapToDouble(i -> i.getPrice().doubleValue() * i.getQuantity())
                        .sum())
                        .thenComparing(o -> -o.getItems().size()))
                .collect(Collectors.toList());
        return new EmployeeOrdersJSONExportDTO(employeeName, orders);
    }
}
