package cardealer.services.api;

import cardealer.dto.binding.SuppliersWrapper;
import cardealer.dto.views.localSuppliers.SupplierView;

import java.util.List;

public interface SupplierService {

    void saveSuppliers(SuppliersWrapper supplierModels);

    List<SupplierView> getLocalSuppliers();
}
