package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
    private final ModelParser mapper;
    private final Validator validator;
    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;

    @Autowired
    public AnimalServiceImpl(ModelParser mapper,
                             Validator validator,
                             AnimalRepository animalRepository, PassportRepository passportRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        if (validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        //problem statement doesn't have the requirements for this one
        Animal animal = mapper.convert(dto, Animal.class);
        Passport passport = mapper.convert(dto.getPassport(), Passport.class);
        animal.setPassport(passport);
        passportRepository.save(passport);
        animalRepository.save(animal);
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return animalRepository.findByOwnerPhoneNumber(phoneNumber)
                .stream()
                .map(a -> new AnimalsJSONExportDTO(a.getPassport().getOwnerName(), a.getName(),
                        a.getAge(), a.getPassport().getSerialNumber(), a.getPassport().getRegistrationDate()))
                .sorted(Comparator.comparing(AnimalsJSONExportDTO::getAge)
                        .thenComparing(AnimalsJSONExportDTO::getSerialNumber))
                .collect(Collectors.toList());
    }
}
