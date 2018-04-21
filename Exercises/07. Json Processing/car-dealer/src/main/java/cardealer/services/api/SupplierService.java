package cardealer.services.api;

import cardealer.dto.binding.SupplierCreateBindingModel;
import cardealer.dto.views.SupplierView;

import java.util.List;

public interface SupplierService {

    void saveSuppliers(SupplierCreateBindingModel[] supplierModels);

    List<SupplierView> getLocalSuppliers();
}
