package cardealer.services.api;

import cardealer.dto.views.SaleDiscountView;

import java.util.List;

public interface SaleService {
    void createSales();

    List<SaleDiscountView> getSalesWithDiscounts();
}
