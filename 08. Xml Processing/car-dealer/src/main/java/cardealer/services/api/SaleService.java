package cardealer.services.api;

import cardealer.dto.views.SalesWithDiscounts.SaleDiscountView;

import java.util.List;

public interface SaleService {
    void createSales();

    List<SaleDiscountView> getSalesWithDiscounts();
}
