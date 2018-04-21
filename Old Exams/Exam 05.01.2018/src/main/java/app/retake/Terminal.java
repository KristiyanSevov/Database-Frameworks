package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static app.retake.Config.*;

@Component
public class Terminal implements CommandLineRunner {
    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final AnimalController animalController;
    private final AnimalAidController animalAidController;
    private final VetController vetController;
    private final ProcedureController procedureController;

    @Autowired
    public Terminal(FileIO fileIO,
                    ConsoleIO consoleIO,
                    AnimalController animalController,
                    AnimalAidController animalAidController,
                    VetController vetController,
                    ProcedureController procedureController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.animalController = animalController;
        this.animalAidController = animalAidController;
        this.vetController = vetController;
        this.procedureController = procedureController;
    }

    @Override
    public void run(String... strings) throws Exception {
        consoleIO.write(animalAidController.importDataFromJSON(fileIO.read(ANIMAL_AIDS_IMPORT_JSON)));
        consoleIO.write(animalController.importDataFromJSON(fileIO.read(ANIMALS_IMPORT_JSON)));
        consoleIO.write(vetController.importDataFromXML(fileIO.read(VETS_IMPORT_XML)));
        consoleIO.write(procedureController.importDataFromXML(fileIO.read(PROCEDURES_IMPORT_XML)));
        fileIO.write(animalController.exportAnimalsByOwnerPhoneNumber("0887446123"),
                "/files/json/animals_export.json");
        fileIO.write(procedureController.exportProcedures(), "/files/xml/procedures_export.xml");
    }
}
