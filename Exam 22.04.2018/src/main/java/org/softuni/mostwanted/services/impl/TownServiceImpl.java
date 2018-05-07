package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.models.dto.TownsExportJSONDto;
import org.softuni.mostwanted.models.dto.TownsJSONImportDto;
import org.softuni.mostwanted.models.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TownServiceImpl implements TownService {
    private final ModelParser mapper;
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(ModelParser mapper, TownRepository townRepository) {
        this.mapper = mapper;
        this.townRepository = townRepository;
    }

    @Override
    public void create(TownsJSONImportDto model) {
        if (!ValidationUtil.isValid(model)) {
            throw new IllegalArgumentException();
        }
        Town town = townRepository.findByName(model.getName());
        if (town != null) {
            throw new IllegalStateException("duplicate data");
        }
        town = mapper.convert(model, Town.class);
        townRepository.save(town);
    }

    @Override
    public List<TownsExportJSONDto> exportRacingTowns() {
        return townRepository.findAll()
                .stream()
                .filter(t -> t.getRacers().size() != 0)
                .map(t -> new TownsExportJSONDto(t.getName(), t.getRacers().size()))
                .sorted(Comparator.comparing((TownsExportJSONDto::getRacers), Comparator.reverseOrder()).thenComparing(TownsExportJSONDto::getName))
                .collect(Collectors.toList());
    }
}
