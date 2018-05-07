package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.models.dto.*;
import org.softuni.mostwanted.models.entities.District;
import org.softuni.mostwanted.models.entities.Racer;
import org.softuni.mostwanted.models.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacerServiceImpl implements RacerService {

    private final ModelParser mapper;
    private final RacerRepository racerRepository;
    private final TownRepository townRepository;

    @Autowired
    public RacerServiceImpl(ModelParser mapper,
                            RacerRepository racerRepository,
                            TownRepository townRepository) {
        this.mapper = mapper;
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void create(RacersImportJSONDto model) {
        if (!ValidationUtil.isValid(model)) {
            throw new IllegalArgumentException();
        }
        Town town = townRepository.findByName(model.getTownName());
        Racer racer = racerRepository.findByName(model.getName());
        if (racer != null) {
            throw new IllegalStateException("duplicate data");
        }
        racer = mapper.convert(model, Racer.class);
        racer.setHomeTown(town);
        racerRepository.save(racer);
    }

    @Override
    public List<RacersWithCarsExportJSONDto> getRacersWithCars() {
        return racerRepository.findAll()
                .stream()
                .filter(r -> r.getCars().size() != 0)
                .map(r -> new RacersWithCarsExportJSONDto(r.getName(), r.getAge(),
                        r.getCars()
                                .stream()
                                .map(c -> String.format("%s %s %s",
                                        c.getBrand(), c.getModel(), c.getYearOfProduction()))
                                .collect(Collectors.toList())))
                .sorted(Comparator.comparing((RacersWithCarsExportJSONDto r) -> -r.getCars().size())
                        .thenComparing(RacersWithCarsExportJSONDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public RacerExportXMLWrapper getMostWantedRacer() {
        RacerExportXmlDto mostWanted = this.racerRepository.findAll()
                .stream()
                .map(r -> new RacerExportXmlDto(r.getName(),
                        r.getEntries()
                                .stream()
                                .map(e -> new EntryExportXMLDto(e.getFinishTime(),
                                        String.format("%s %s @ %s", e.getCar().getBrand(), e.getCar().getModel(),
                                                e.getCar().getYearOfProduction())))
                                .sorted(Comparator.comparing(EntryExportXMLDto::getFinishTime))
                                .collect(Collectors.toList())))
                .sorted(Comparator.comparing(r -> -r.getEntries().size()))
                .findFirst().get();

        RacerExportXMLWrapper wrapper = new RacerExportXMLWrapper();
        wrapper.getRacers().add(mostWanted);
        return wrapper;
    }
}
