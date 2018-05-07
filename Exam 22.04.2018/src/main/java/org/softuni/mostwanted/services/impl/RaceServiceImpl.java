package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.models.dto.EntryImportXmlDto;
import org.softuni.mostwanted.models.dto.RaceImportXMLDto;
import org.softuni.mostwanted.models.entities.District;
import org.softuni.mostwanted.models.entities.Race;
import org.softuni.mostwanted.models.entities.RaceEntry;
import org.softuni.mostwanted.models.entities.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RaceServiceImpl implements RaceService{
    private final ModelParser mapper;
    private final RaceRepository raceRepository;
    private final DistrictRepository districtRepository;
    private final RaceEntryRepository raceEntryRepository;

    @Autowired
    public RaceServiceImpl(ModelParser mapper,
                           RaceRepository raceRepository,
                           DistrictRepository districtRepository,
                           RaceEntryRepository raceEntryRepository) {
        this.mapper = mapper;
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
    }

    @Override
    public Integer create(RaceImportXMLDto model) { //doesn't make sense to check for duplicates
        if (!ValidationUtil.isValid(model)){
            throw new IllegalArgumentException();
        }
        District district = districtRepository.findByName(model.getDistrictName());
        if (district == null){
            throw new IllegalArgumentException();
        }
        Race race = mapper.convert(model, Race.class);
        race.setDistrict(district);
        for (EntryImportXmlDto entry : model.getEntries()) {
            RaceEntry raceEntry = raceEntryRepository.findOne(entry.getId());
            race.getEntries().add(raceEntry);
        }
        return raceRepository.save(race).getId();
    }
}
