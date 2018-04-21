package app.retake.services.impl;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.VetRepository;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    private final Validator validator;
    private final ModelParser mapper;
    private final VetRepository vetRepository;

    @Autowired
    public VetServiceImpl(Validator validator,
                          ModelParser mapper,
                          VetRepository vetRepository) {
        this.validator = validator;
        this.mapper = mapper;
        this.vetRepository = vetRepository;
    }

    @Override
    public void create(VetXMLImportDTO dto) {
        if (validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }
        Vet vet = mapper.convert(dto, Vet.class);
        vetRepository.save(vet);
    }
}
