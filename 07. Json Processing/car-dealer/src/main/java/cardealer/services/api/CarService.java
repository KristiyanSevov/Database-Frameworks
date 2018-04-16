package cardealer.services.api;

import cardealer.dto.binding.CarCreateBindingModel;
import cardealer.dto.views.CarView;
import cardealer.dto.views.CarWithPartsView;

import java.util.List;

public interface CarService {
    void saveCars(CarCreateBindingModel[] models);

    List<CarView> getToyotaCars();

    List<CarWithPartsView> getCarsWithParts();
}
