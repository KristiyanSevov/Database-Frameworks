package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.models.entities.Car;
import org.softuni.mostwanted.models.entities.RaceEntry;
import org.softuni.mostwanted.models.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer>{
    RaceEntry findByCarAndRacer(Car car, Racer racer);
}
