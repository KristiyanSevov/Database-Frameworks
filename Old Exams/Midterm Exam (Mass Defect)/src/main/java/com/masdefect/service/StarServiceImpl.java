package com.masdefect.service;

import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.repository.StarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StarServiceImpl implements StarService {

    private final ModelParser mapper;
    private final StarRepository starRepository;
    private final SolarSystemRepository solarSystemRepository;

    public StarServiceImpl(ModelParser mapper,
                           StarRepository starRepository,
                           SolarSystemRepository solarSystemRepository) {
        this.mapper = mapper;
        this.starRepository = starRepository;
        this.solarSystemRepository = solarSystemRepository;
    }

    @Override
    public void create(StarImportJSONDto model) {
        SolarSystem solarSystem = solarSystemRepository.findByName(model.getSolarSystem());
        if (model.getName() == null || solarSystem == null) {
            throw new IllegalArgumentException();
        }
        Star star = new Star();
        star.setName(model.getName());
        star.setSolarSystem(solarSystem);
        starRepository.save(star);
    }
}
