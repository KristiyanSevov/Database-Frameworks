package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.models.dto.RaceEntryImportXMLDto;

public interface RaceEntryService {
    Integer create(RaceEntryImportXMLDto model);
}
