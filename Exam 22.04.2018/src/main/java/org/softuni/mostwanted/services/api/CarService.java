package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.models.dto.CarsImportJSONDto;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;

public interface CarService {
    void create(CarsImportJSONDto model);
}
