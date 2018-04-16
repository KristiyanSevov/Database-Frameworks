package cardealer.services.api;

import cardealer.dto.binding.PartCreateBindingModel;

public interface PartService {
    void saveParts(PartCreateBindingModel[] models);
}
