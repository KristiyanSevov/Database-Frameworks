package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Position;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.PositionRepository;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Validator validator;
    private final ModelParser mapper;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public EmployeeServiceImpl(Validator validator,
                               ModelParser mapper, EmployeeRepository employeeRepository,
                               PositionRepository positionRepository) {
        this.validator = validator;
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void create(EmployeeJSONImportDTO importDTO) {
        if (validator.validate(importDTO).size() != 0){
            throw new IllegalArgumentException();
        }
        Position position = positionRepository.findByName(importDTO.getPosition());
        if (position == null){
            position  = new Position();
            position.setName(importDTO.getPosition());
            positionRepository.saveAndFlush(position);
        }
        Employee employee = mapper.convert(importDTO, Employee.class);
        employee.setPosition(position);
        employeeRepository.save(employee);
    }

    @Override
    public void createMany(EmployeeJSONImportDTO[] importDTO) {

    }
}
