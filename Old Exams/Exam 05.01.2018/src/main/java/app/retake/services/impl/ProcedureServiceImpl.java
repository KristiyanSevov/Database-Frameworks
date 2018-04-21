package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.*;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.*;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final ModelParser mapper;
    private final ProcedureRepository procedureRepository;
    private final VetRepository vetRepository;
    private final AnimalRepository animalRepository;
    private final AnimalAidRepository animalAidRepository;
    private final PassportRepository passportRepository;

    @Autowired
    public ProcedureServiceImpl(ModelParser mapper,
                                ProcedureRepository procedureRepository,
                                VetRepository vetRepository,
                                AnimalRepository animalRepository,
                                AnimalAidRepository animalAidRepository, PassportRepository passportRepository) {
        this.mapper = mapper;
        this.procedureRepository = procedureRepository;
        this.vetRepository = vetRepository;
        this.animalRepository = animalRepository;
        this.animalAidRepository = animalAidRepository;
        this.passportRepository = passportRepository;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        Vet vet = vetRepository.findByName(dto.getVet());
        Passport passport = passportRepository.findOne(dto.getAnimal());
        if (vet == null || passport == null) {
            throw new IllegalArgumentException();
        }
        Animal animal = passport.getAnimal();
        Set<AnimalAid> animalAids = new HashSet<>();
        for (ProcedureAnimalAidXMLImportDTO model : dto.getAnimalAids()) {
            AnimalAid animalAid = animalAidRepository.findByName(model.getName());
            if (animalAid == null) {
                throw new IllegalArgumentException();
            }
            animalAids.add(animalAid);
        }
        Procedure procedure = new Procedure(vet, animal, animalAids);
        animal.getProcedures().add(procedure);
        for (AnimalAid aid : animalAids) {
            aid.getProcedures().add(procedure);
        }
        procedureRepository.save(procedure);
        animalAidRepository.save(animalAids);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        List<ProcedureXMLExportDTO> views = procedureRepository.findAll()
                .stream()
                .map(p -> new ProcedureXMLExportDTO(p.getAnimal().getPassport().getSerialNumber(),
                        p.getAnimal().getPassport().getOwnerPhoneNumber(),
                        p.getServices()
                                .stream()
                                .map(s -> new ProcedureAnimalAidXMLExportDTO(s.getName(), s.getPrice()))
                                .sorted(Comparator.comparing(ProcedureAnimalAidXMLExportDTO::getPrice,
                                        Comparator.reverseOrder()))
                                .collect(Collectors.toList())))
                .sorted(Comparator.comparing(p -> p.getAnimalAids()
                        .stream()
                        .mapToDouble(ProcedureAnimalAidXMLExportDTO::getPrice)
                        .sum()))
                .collect(Collectors.toList());
        return new ProcedureWrapperXMLExportDTO(views);
    }
}

