package cardealer.services.impl;

import cardealer.dto.binding.PartCreateBindingModel;
import cardealer.dto.binding.PartsWrapper;
import cardealer.entities.Part;
import cardealer.entities.Supplier;
import cardealer.parsers.ModelParser;
import cardealer.repositories.PartRepository;
import cardealer.repositories.SupplierRepository;
import cardealer.services.api.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private final ModelParser mapper;
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(ModelParser mapper, PartRepository partRepository, SupplierRepository supplierRepository) {
        this.mapper = mapper;
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void saveParts(PartsWrapper models) {
        Random random = new Random();
        long count = supplierRepository.count();
        for (PartCreateBindingModel model : models.getParts()) {
            Part part = mapper.convert(model, Part.class);
            Supplier supplier = supplierRepository.findById(random.nextInt((int)count) + 1).get();
            part.setSupplier(supplier);
            partRepository.save(part);
        }
    }
}
