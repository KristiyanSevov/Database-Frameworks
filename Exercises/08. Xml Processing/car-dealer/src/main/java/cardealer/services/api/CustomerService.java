package cardealer.services.api;

import cardealer.dto.binding.CustomersWrapper;
import cardealer.dto.views.orderedCustomers.CustomerView;
import cardealer.dto.views.salesByCustomer.CustomerWithSalesView;

import java.util.List;

public interface CustomerService {
    void saveCustomers(CustomersWrapper models);

    List<CustomerView> getOrderedCustomers();

    List<CustomerWithSalesView> getCustomersWithSales();
}
