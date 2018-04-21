package com.masdefect.service;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.domain.dto.xml.VictimXMLDto;
import com.masdefect.domain.entities.Anomaly;
import com.masdefect.domain.entities.Person;
import com.masdefect.domain.entities.Planet;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.repository.AnomalyRepository;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnomalyServiceImpl implements AnomalyService{
    private final AnomalyRepository anomalyRepository;
    private final PlanetRepository planetRepository;
    private final PersonRepository personRepository;

    public AnomalyServiceImpl(AnomalyRepository anomalyRepository,
                              PlanetRepository planetRepository,
                              PersonRepository personRepository) {
        this.anomalyRepository = anomalyRepository;
        this.planetRepository = planetRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void create(AnomalyImportJSONDto model) {
        Planet origin = planetRepository.findByName(model.getOriginPlanet());
        Planet teleport = planetRepository.findByName(model.getTeleportPlanet());
        if (origin == null || teleport == null) {
            throw new IllegalArgumentException();
        }
        Anomaly anomaly = new Anomaly();
        anomaly.setOriginPlanet(origin);
        anomaly.setTeleportPlanet(teleport);
        anomalyRepository.save(anomaly);
    }

    @Override
    public void create(AnomalyVictimsJSONDto model) {
        Anomaly anomaly = anomalyRepository.findOne(model.getId());
        Person person = personRepository.findByName(model.getPerson());
        if (anomaly == null || person == null){
            throw new IllegalArgumentException();
        }
        anomaly.getVictims().add(person);
        anomalyRepository.save(anomaly);
    }

    @Override
    public void create(AnomalyXMLDto model) {
        Planet origin = planetRepository.findByName(model.getOriginPlanet());
        Planet teleport = planetRepository.findByName(model.getTeleportPlanet());
        if (origin == null || teleport == null) {
            throw new IllegalArgumentException();
        }
        Set<Person> people = new HashSet<>();
        for (VictimXMLDto victim: model.getVictims()) {
            Person person = personRepository.findByName(victim.getName());
            if (person == null){
                throw new IllegalArgumentException();
            }
            people.add(person);
        }
        Anomaly anomaly = new Anomaly();
        anomaly.setOriginPlanet(origin);
        anomaly.setTeleportPlanet(teleport);
        anomaly.setVictims(people);
        anomalyRepository.save(anomaly);
    }

    @Override
    public AnomalyExportJSONDto findMostAffectingAnomalies() {
        Anomaly anomaly = anomalyRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(a -> -a.getVictims().size()))
                .findFirst().get();
        return new AnomalyExportJSONDto(anomaly.getId(),
                new PlanetExportJSONDto(anomaly.getOriginPlanet().getName()),
                new PlanetExportJSONDto(anomaly.getTeleportPlanet().getName()),
                anomaly.getVictims().size());
    }

    @Override
    public AnomaliesXMLDto finaAllAnomalies() {
        return new AnomaliesXMLDto(anomalyRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Anomaly::getId))
                .map(a -> new AnomalyXMLDto(a.getOriginPlanet().getName(),
                        a.getTeleportPlanet().getName()))
                .collect(Collectors.toList()));
    }
}
