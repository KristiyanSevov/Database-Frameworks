package cardealer.services.impl;

import cardealer.dto.binding.CarCreateBindingModel;
import cardealer.dto.views.CarView;
import cardealer.dto.views.CarWithPartsView;
import cardealer.dto.views.PartView;
import cardealer.dto.views.PlainCarView;
import cardealer.entities.Car;
import cardealer.entities.Part;
import cardealer.parsers.ModelParser;
import cardealer.repositories.CarRepository;
import cardealer.repositories.PartRepository;
import cardealer.services.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final ModelParser mapper;
    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(ModelParser mapper, CarRepository carRepository, PartRepository partRepository) {
        this.mapper = mapper;
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    @Override
    public void saveCars(CarCreateBindingModel[] models) {
        Random random = new Random();
        List<Part> allParts = partRepository.findAll();
        for (CarCreateBindingModel model : models) {
            Car car = mapper.convert(model, Car.class);
            for (int i = 0; i < 20; i++) {
                Part part = allParts.get(random.nextInt(allParts.size()));
                part.getCars().add(car);
            }
            this.carRepository.save(car);
        }
        this.partRepository.saveAll(allParts);
    }

    @Override
    public List<CarView> getToyotaCars() {
        return carRepository.findByMake("Toyota")
                .stream()
                .map(c -> new CarView(c.getId(), c.getMake(), c.getModel(), c.getTravelledDistance()))
                .sorted(Comparator.comparing(CarView::getModel)
                        .thenComparing(CarView::getTravelledDistance, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarWithPartsView> getCarsWithParts() {
        return carRepository.findAll()
                .stream()
                .map(c -> new CarWithPartsView(
                        new PlainCarView(c.getMake(), c.getModel(), c.getTravelledDistance()),
                        c.getParts()
                                .stream()
                                .map(p -> new PartView(p.getName(), p.getPrice()))
                                .collect(Collectors.toList())
                )).collect(Collectors.toList());

    }
}
