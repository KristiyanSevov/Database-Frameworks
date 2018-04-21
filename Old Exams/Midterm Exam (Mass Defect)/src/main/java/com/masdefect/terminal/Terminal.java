package com.masdefect.terminal;

import com.masdefect.controller.*;
import com.masdefect.io.interfaces.ConsoleIO;
import com.masdefect.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.masdefect.config.Config.*;

@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final StarsController starsController;
    private final SolarSystemController solarSystemController;
    private final PersonsController personsController;
    private final PlanetsController planetsController;
    private final AnomalyVictimsController anomalyVictimsController;
    private final AnomalyController anomalyController;

    @Autowired
    public Terminal(FileIO fileIO,
                    ConsoleIO consoleIO,
                    StarsController starsController,
                    SolarSystemController solarSystemController,
                    PersonsController personsController,
                    PlanetsController planetsController,
                    AnomalyVictimsController anomalyVictimsController,
                    AnomalyController anomalyController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.starsController = starsController;
        this.solarSystemController = solarSystemController;
        this.personsController = personsController;
        this.planetsController = planetsController;
        this.anomalyVictimsController = anomalyVictimsController;
        this.anomalyController = anomalyController;
    }

    @Override
    public void run(String... args) throws Exception {
        consoleIO.write(solarSystemController.importDataFromJSON(fileIO.read(SOLAR_SYSTEM_IMPORT_JSON)));
        consoleIO.write(starsController.importDataFromJSON(fileIO.read(STARS_IMPORT_JSON)));
        consoleIO.write(planetsController.importDataFromJSON(fileIO.read(PLANETS_IMPORT_JSON)));
        consoleIO.write(personsController.importDataFromJSON(fileIO.read(PERSONS_IMPORT_JSON)));
        consoleIO.write(anomalyController.importDataFromJSON(fileIO.read(ANOMALY_IMPORT_JSON)));
        consoleIO.write(anomalyVictimsController.importDataFromJSON(fileIO.read(ANOMALY_VICTIMS_IMPORT_JSON)));
        consoleIO.write(anomalyController.importDataFromXML(fileIO.read(ANOMALIES_IMPORT_XML)));
        fileIO.write(planetsController.planetsWithNoPeopleTeleportedToThem(), "/planets.json");
        fileIO.write(anomalyController.findAnomalyWithMostVictims(), "/anomalies.json");
        fileIO.write(anomalyController.exportAnomaliesOrdered(), "/anomalies.xml");
    }
}
