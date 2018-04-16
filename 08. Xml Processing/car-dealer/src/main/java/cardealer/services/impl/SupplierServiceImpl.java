package cardealer.services.impl;

import cardealer.dto.binding.SupplierCreateBindingModel;
import cardealer.dto.binding.SuppliersWrapper;
import cardealer.dto.views.localSuppliers.SupplierView;
import cardealer.entities.Supplier;
import cardealer.parsers.ModelParser;
import cardealer.repositories.SupplierRepository;
import cardealer.services.api.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final ModelParser mapper;
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(ModelParser mapper, SupplierRepository supplierRepository) {
        this.mapper = mapper;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void saveSuppliers(SuppliersWrapper supplierModels) {
        for (SupplierCreateBindingModel model : supplierModels.getSuppliers()) {
            Supplier supplier = mapper.convert(model, Supplier.class);
            supplierRepository.save(supplier);
        }
    }

    @Override
    public List<SupplierView> getLocalSuppliers() {
        return supplierRepository.findByIsImporter(false)
                .stream()
                .map(s -> new SupplierView(s.getId(), s.getName(), s.getParts().size()))
                .collect(Collectors.toList());
    }
}
