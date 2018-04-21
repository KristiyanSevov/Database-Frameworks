package com.masdefect.service;

import com.masdefect.domain.dto.json.PersonExportJSONDto;
import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.domain.entities.Person;
import com.masdefect.domain.entities.Planet;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PlanetRepository planetRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PlanetRepository planetRepository) {
        this.personRepository = personRepository;
        this.planetRepository = planetRepository;
    }

    @Override
    public void create(PersonImportJSONDto model) {
        Planet planet = planetRepository.findByName(model.getHomePlanet());
        if (model.getName() == null || planet == null) {
            throw new IllegalArgumentException();
        }
        Person person = new Person();
        person.setName(model.getName());
        person.setHomePlanet(planet);
        personRepository.save(person);
    }

    @Override
    public List<PersonExportJSONDto> findInnocentPersons() {
        return null;
    }
}
