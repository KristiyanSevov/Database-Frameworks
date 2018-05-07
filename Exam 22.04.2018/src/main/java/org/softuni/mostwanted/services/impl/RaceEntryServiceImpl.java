package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.models.dto.RaceEntryImportXMLDto;
import org.softuni.mostwanted.models.entities.Car;
import org.softuni.mostwanted.models.entities.RaceEntry;
import org.softuni.mostwanted.models.entities.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RaceEntryServiceImpl implements RaceEntryService {
    private final ModelParser mapper;
    private final RaceEntryRepository raceEntryRepository;
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;

    @Autowired
    public RaceEntryServiceImpl(ModelParser mapper,
                                RaceEntryRepository raceEntryRepository,
                                RacerRepository racerRepository, CarRepository carRepository) {
        this.mapper = mapper;
        this.raceEntryRepository = raceEntryRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Integer create(RaceEntryImportXMLDto model) {
        if (!ValidationUtil.isValid(model)){
            throw new IllegalArgumentException();
        }
        Racer racer = racerRepository.findByName(model.getRacer());
        Car car = carRepository.findOne(model.getCarId());
        RaceEntry duplicateCheck = raceEntryRepository.findByCarAndRacer(car, racer);
        if (duplicateCheck != null){
            throw new IllegalStateException("duplicate data");
        }
        RaceEntry entry = mapper.convert(model, RaceEntry.class);
        entry.setRacer(racer);
        return raceEntryRepository.save(entry).getId();
    }
}
