package cardealer;

import cardealer.dto.binding.*;
import cardealer.dto.views.SalesWithDiscounts.SaleDiscountView;
import cardealer.dto.views.SalesWithDiscounts.SalesDiscountsExportWrapper;
import cardealer.dto.views.carsToyota.CarExportWrapper;
import cardealer.dto.views.carsToyota.CarView;
import cardealer.dto.views.carsWithParts.CarWithPartsView;
import cardealer.dto.views.carsWithParts.CarWithPartsWrapper;
import cardealer.dto.views.localSuppliers.SupplierExportWrapper;
import cardealer.dto.views.localSuppliers.SupplierView;
import cardealer.dto.views.orderedCustomers.CustomerExportWrapper;
import cardealer.dto.views.orderedCustomers.CustomerView;
import cardealer.dto.views.salesByCustomer.CustomerWithSalesView;
import cardealer.dto.views.salesByCustomer.CustomerWithSalesWrapper;
import cardealer.io.FileIO;
import cardealer.parsers.XmlParser;
import cardealer.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final FileIO fileIO;
    private final XmlParser parser;
    private final CarService carService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public Runner(FileIO fileIO,
                  XmlParser parser,
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

    private void exportSalesWithDiscounts() throws IOException, JAXBException {
        List<SaleDiscountView> sales = saleService.getSalesWithDiscounts();
        SalesDiscountsExportWrapper wrapper = new SalesDiscountsExportWrapper(sales);
        String salesXml = parser.write(wrapper);
        fileIO.write(salesXml, "/output/sales-discounts.xml");
    }

    private void exportTotalSalesByCustomer() throws IOException, JAXBException {
        List<CustomerWithSalesView> customersWithSales = customerService.getCustomersWithSales();
        CustomerWithSalesWrapper wrapper = new CustomerWithSalesWrapper(customersWithSales);
        String customersWithSalesXml = parser.write(wrapper);
        fileIO.write(customersWithSalesXml, "/output/customers-total-sales.xml");
    }

    private void exportCarsWithParts() throws IOException, JAXBException {
        List<CarWithPartsView> carsWithParts = carService.getCarsWithParts();
        CarWithPartsWrapper wrapper = new CarWithPartsWrapper(carsWithParts);
        String carsWithPartsXml = parser.write(wrapper);
        fileIO.write(carsWithPartsXml, "/output/cars-and-parts.xml");
    }

    private void exportLocalSuppliers() throws IOException, JAXBException {
        List<SupplierView> suppliers = supplierService.getLocalSuppliers();
        SupplierExportWrapper wrapper = new SupplierExportWrapper(suppliers);
        String supplierXml = parser.write(wrapper);
        fileIO.write(supplierXml, "/output/local-suppliers.xml");
    }

    private void exportToyotaCars() throws IOException, JAXBException {
        List<CarView> cars = carService.getToyotaCars();
        CarExportWrapper wrapper = new CarExportWrapper(cars);
        String carsXml = parser.write(wrapper);
        fileIO.write(carsXml, "/output/toyota-cars.xml");
    }

    private void exportOrderedCustomers() throws IOException, JAXBException {
        List<CustomerView> customers = customerService.getOrderedCustomers();
        CustomerExportWrapper wrapper = new CustomerExportWrapper(customers);
        String customersXml = parser.write(wrapper);
        fileIO.write(customersXml, "/output/ordered-customers.xml");
    }

    private void createSales() {
        saleService.createSales();
    }

    private void importCustomers() throws IOException, JAXBException {
        String customerXml = fileIO.read("/input/customers.xml");
        CustomersWrapper models = parser.read(CustomersWrapper.class, customerXml);
        customerService.saveCustomers(models);
    }

    private void importParts() throws IOException, JAXBException {
        String partsXml = fileIO.read("/input/parts.xml");
        PartsWrapper models = parser.read(PartsWrapper.class, partsXml);
        partService.saveParts(models);
    }

    private void importSuppliers() throws IOException, JAXBException {
        String suppliersXml = fileIO.read("/input/suppliers.xml");
        SuppliersWrapper wrapper = parser.read(SuppliersWrapper.class, suppliersXml);
        supplierService.saveSuppliers(wrapper);
    }

    private void importCars() throws IOException, JAXBException {
        String carsXml = fileIO.read("/input/cars.xml");
        CarsWrapper models = parser.read(CarsWrapper.class, carsXml);
        carService.saveCars(models);

    }
}
