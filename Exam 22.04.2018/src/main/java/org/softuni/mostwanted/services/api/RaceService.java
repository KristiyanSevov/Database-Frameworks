package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.models.dto.RaceImportXMLDto;

public interface RaceService {
    Integer create(RaceImportXMLDto model);
}
