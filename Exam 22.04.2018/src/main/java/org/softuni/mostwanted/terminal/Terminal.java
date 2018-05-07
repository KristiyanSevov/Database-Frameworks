package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.models.entities.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.softuni.mostwanted.config.Config.*;

@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final CarController carController;
    private final DistrictController districtController;
    private final RaceController raceController;
    private final RaceEntryController raceEntryController;
    private final RacerController racerController;
    private final TownController townController;

    @Autowired
    public Terminal(FileIO fileIO,
                ConsoleIO consoleIO,
                CarController carController,
                DistrictController districtController,
                RaceController raceController,
                RaceEntryController raceEntryController,
                RacerController racerController,
                TownController townController) {
            this.fileIO = fileIO;
            this.consoleIO = consoleIO;
            this.carController = carController;
            this.districtController = districtController;
            this.raceController = raceController;
            this.raceEntryController = raceEntryController;
            this.racerController = racerController;
            this.townController = townController;
        }

        @Override
        public void run(String... args) throws Exception {
            consoleIO.write(townController.importTownsFromJSON(fileIO.read(TOWNS_IMPORT_JSON)));
            consoleIO.write(districtController.importDistrictsFromJSON(fileIO.read(DISTRICTS_IMPORT_JSON)));
            consoleIO.write(racerController.importRacersFromJSON(fileIO.read(RACERS_IMPORT_JSON)));
            consoleIO.write(carController.importCarsFromJSON(fileIO.read(CARS_IMPORT_JSON)));
            consoleIO.write(raceEntryController.importRaceEntriesFromXML(fileIO.read(RACE_ENTRIES_IMPORT_XML)));
            consoleIO.write(raceController.importRacesFromXML(fileIO.read(RACES_IMPORT_XML)));
            fileIO.write(townController.exportRacingTowns(), TOWNS_EXPORT_JSON);
            fileIO.write(racerController.exportRacersWithCars(), RACERS_EXPORT_JSON);
            fileIO.write(racerController.exportMostWantedRacer(), RACER_EXPORT_XML);
    }
}
