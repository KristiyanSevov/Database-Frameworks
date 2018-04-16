package cardealer.services.api;

import cardealer.dto.binding.CustomerCreateBindingModel;
import cardealer.dto.views.CustomerView;
import cardealer.dto.views.CustomerWithSalesView;

import java.util.List;

public interface CustomerService {
    void saveCustomers(CustomerCreateBindingModel[] models);

    List<CustomerView> getOrderedCustomers();

    List<CustomerWithSalesView> getCustomersWithSales();
}
