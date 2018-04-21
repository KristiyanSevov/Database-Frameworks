package com.masdefect.service;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.domain.entities.Planet;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.repository.StarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanetServiceImpl implements PlanetService {

    private final ModelParser mapper;
    private final StarRepository starRepository;
    private final PlanetRepository planetRepository;
    private final SolarSystemRepository solarSystemRepository;

    public PlanetServiceImpl(ModelParser mapper,
                             StarRepository starRepository,
                             PlanetRepository planetRepository, SolarSystemRepository solarSystemRepository) {
        this.mapper = mapper;
        this.starRepository = starRepository;
        this.planetRepository = planetRepository;
        this.solarSystemRepository = solarSystemRepository;
    }
    @Override
    public void create(PlanetImportJSONDto model) {
        SolarSystem solarSystem = solarSystemRepository.findByName(model.getSolarSystem());
        Star star = starRepository.findByName(model.getSun());
        if (model.getName() == null || solarSystem == null || star == null) {
            throw new IllegalArgumentException();
        }
        Planet planet = new Planet();
        planet.setName(model.getName());
        planet.setSolarSystem(solarSystem);
        planet.setSun(star);
        planetRepository.save(planet);
    }

    @Override
    public List<PlanetExportJSONDto> findAllPlanetsWithoutPeopleTeleportedFromThem() {
        return planetRepository.findAll().stream()
                .filter(p -> p.getOriginAnomalies().size() == 0)
                .map(p -> new PlanetExportJSONDto(p.getName()))
                .collect(Collectors.toList());
    }
}
