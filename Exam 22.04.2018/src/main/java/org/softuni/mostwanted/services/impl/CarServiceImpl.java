package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.models.dto.CarsImportJSONDto;
import org.softuni.mostwanted.models.entities.Car;
import org.softuni.mostwanted.models.entities.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final ModelParser mapper;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;

    public CarServiceImpl(ModelParser mapper,
                          CarRepository carRepository,
                          RacerRepository racerRepository) {
        this.mapper = mapper;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
    }

    @Override
    public void create(CarsImportJSONDto model) {
        if (!ValidationUtil.isValid(model)){
            throw new IllegalArgumentException();
        }
        Car car = carRepository.findByBrandAndModelAndYearOfProduction(model.getBrand(),
                model.getModel(), model.getYearOfProduction());
        if (car != null){
            throw new IllegalStateException("duplicate data");
        }
        Racer racer = racerRepository.findByName(model.getRacerName());
        car = mapper.convert(model, Car.class);
        car.setRacer(racer);
        carRepository.save(car);
    }
}
