package app.exam.terminal;

import app.exam.controller.CategoryController;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.ConsoleIO;
import app.exam.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static app.exam.config.Config.EMPLOYEES_IMPORT_JSON;
import static app.exam.config.Config.ITEMS_IMPORT_JSON;
import static app.exam.config.Config.ORDERS_IMPORT_XML;

@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final EmployeesController employeesController;
    private final ItemsController itemsController;
    private final OrdersController ordersController;
    private final CategoryController categoryController;

    @Autowired
    public Terminal(FileIO fileIO,
                    ConsoleIO consoleIO,
                    EmployeesController employeesController,
                    ItemsController itemsController,
                    OrdersController ordersController,
                    CategoryController categoryController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.employeesController = employeesController;
        this.itemsController = itemsController;
        this.ordersController = ordersController;
        this.categoryController = categoryController;
    }

    @Override
    public void run(String... args) throws Exception {
        employeesController.importDataFromJSON(fileIO.read(EMPLOYEES_IMPORT_JSON));
        itemsController.importDataFromJSON(fileIO.read(ITEMS_IMPORT_JSON));
        ordersController.importDataFromXML(fileIO.read(ORDERS_IMPORT_XML));
        fileIO.write(ordersController
                .exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo"), "/orders.json");
        List<String> categories = new ArrayList<>();
        categories.add("Chicken");
        categories.add("Toys");
        categories.add("Drinks");
        fileIO.write(categoryController.getCategoriesWithMostPopularItemsSorted(categories), "/categories.xml");
    }
}
