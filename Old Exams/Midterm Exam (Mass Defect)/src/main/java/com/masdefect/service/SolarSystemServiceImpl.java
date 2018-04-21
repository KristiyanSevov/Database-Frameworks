package com.masdefect.service;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.SolarSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SolarSystemServiceImpl implements SolarSystemService {
    private final ModelParser mapper;
    private final SolarSystemRepository solarSystemRepository;

    @Autowired
    public SolarSystemServiceImpl(ModelParser mapper, SolarSystemRepository solarSystemRepository) {
        this.mapper = mapper;
        this.solarSystemRepository = solarSystemRepository;
    }

    @Override
    public void create(SolarSystemImportJSONDto model) {
        if (model.getName() == null){
            throw new IllegalArgumentException();
        }
        SolarSystem solarSystem = mapper.convert(model, SolarSystem.class);
        solarSystemRepository.save(solarSystem);
    }
}
