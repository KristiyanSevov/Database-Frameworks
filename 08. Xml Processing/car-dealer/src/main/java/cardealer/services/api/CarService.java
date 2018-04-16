package cardealer.services.api;

import cardealer.dto.binding.CarsWrapper;
import cardealer.dto.views.carsToyota.CarView;
import cardealer.dto.views.carsWithParts.CarWithPartsView;

import java.util.List;

public interface CarService {
    void saveCars(CarsWrapper models);

    List<CarView> getToyotaCars();

    List<CarWithPartsView> getCarsWithParts();
}
