package cardealer;

import cardealer.dto.binding.CarCreateBindingModel;
import cardealer.dto.binding.CustomerCreateBindingModel;
import cardealer.dto.binding.PartCreateBindingModel;
import cardealer.dto.binding.SupplierCreateBindingModel;
import cardealer.dto.views.*;
import cardealer.io.FileIO;
import cardealer.parsers.JsonParser;
import cardealer.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final FileIO fileIO;
    private final JsonParser parser;
    private final CarService carService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public Runner(FileIO fileIO,
                  JsonParser parser,
                  CarService carService,
                  SupplierService supplierService,
                  PartService partService,
                  CustomerService customerService,
                  SaleService saleService) {
        this.fileIO = fileIO;
        this.parser = parser;
        this.carService = carService;
        this.supplierService = supplierService;
        this.partService = partService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        importSuppliers();
        importParts();
        importCars();
        importCustomers();
        createSales();
        exportOrderedCustomers();
        exportToyotaCars();
        exportLocalSuppliers();
        exportCarsWithParts();
        exportTotalSalesByCustomer();
        exportSalesWithDiscounts();
    }

    private void exportSalesWithDiscounts() throws IOException {
        List<SaleDiscountView> sales = saleService.getSalesWithDiscounts();
        String salesJson = parser.write(sales);
        fileIO.write(salesJson, "/output/sales-discounts.json");
    }

    private void exportTotalSalesByCustomer() throws IOException {
        List<CustomerWithSalesView> customersWithSales = customerService.getCustomersWithSales();
        String customersWithSalesJson = parser.write(customersWithSales);
        fileIO.write(customersWithSalesJson, "/output/customers-total-sales.json");
    }

    private void exportCarsWithParts() throws IOException {
        List<CarWithPartsView> carsWithParts = carService.getCarsWithParts();
        String carsWithPartsJson = parser.write(carsWithParts);
        fileIO.write(carsWithPartsJson, "/output/cars-and-parts.json");
    }

    private void exportLocalSuppliers() throws IOException {
        List<SupplierView> suppliers = supplierService.getLocalSuppliers();
        String supplierJson = parser.write(suppliers);
        fileIO.write(supplierJson, "/output/local-suppliers.json");
    }

    private void exportToyotaCars() throws IOException {
        List<CarView> cars = carService.getToyotaCars();
        String carsJson = parser.write(cars);
        fileIO.write(carsJson, "/output/toyota-cars.json");
    }

    private void exportOrderedCustomers() throws IOException {
        List<CustomerView> customers = customerService.getOrderedCustomers();
        String customersJson = parser.write(customers);
        fileIO.write(customersJson, "/output/ordered-customers.json");
    }

    private void createSales() {
        saleService.createSales();
    }

    private void importCustomers() throws IOException {
        String customerJson = fileIO.read("/input/customers.json");
        CustomerCreateBindingModel[] models = parser.read(CustomerCreateBindingModel[].class, customerJson);
        customerService.saveCustomers(models);
    }

    private void importParts() throws IOException {
        String partsJson = fileIO.read("/input/parts.json");
        PartCreateBindingModel[] models = parser.read(PartCreateBindingModel[].class, partsJson);
        partService.saveParts(models);
    }

    private void importSuppliers() throws IOException {
        String suppliersJson = fileIO.read("/input/suppliers.json");
        SupplierCreateBindingModel[] supplierModels = parser.read(SupplierCreateBindingModel[].class, suppliersJson);
        supplierService.saveSuppliers(supplierModels);
    }

    private void importCars() throws IOException {
        String carsJson = fileIO.read("/input/cars.json");
        CarCreateBindingModel[] models = parser.read(CarCreateBindingModel[].class, carsJson);
        carService.saveCars(models);

    }
}
