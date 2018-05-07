package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.models.dto.RacerExportXMLWrapper;
import org.softuni.mostwanted.models.dto.RacersImportJSONDto;
import org.softuni.mostwanted.models.dto.RacersWithCarsExportJSONDto;

import java.util.List;

public interface RacerService {
    void create(RacersImportJSONDto model);

    List<RacersWithCarsExportJSONDto> getRacersWithCars();

    RacerExportXMLWrapper getMostWantedRacer();
}
