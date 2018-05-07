package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.models.dto.TownsExportJSONDto;
import org.softuni.mostwanted.models.dto.TownsJSONImportDto;

import java.util.List;

public interface TownService {
    void create(TownsJSONImportDto model);

    List<TownsExportJSONDto> exportRacingTowns();
}
