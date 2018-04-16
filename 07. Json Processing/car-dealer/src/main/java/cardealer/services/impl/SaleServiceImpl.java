package cardealer.services.impl;

import cardealer.dto.views.PlainCarView;
import cardealer.dto.views.SaleDiscountView;
import cardealer.entities.Car;
import cardealer.entities.Customer;
import cardealer.entities.Sale;
import cardealer.repositories.CarRepository;
import cardealer.repositories.CustomerRepository;
import cardealer.repositories.SaleRepository;
import cardealer.services.api.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createSales() {
        List<Car> cars = carRepository.findAll();
        List<Customer> customers = customerRepository.findAll();
        List<Double> discounts = Arrays.asList(0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5);
        Random random = new Random();
        int numSalesToCreate = 50;
        for (int i = 0; i < numSalesToCreate; i++) {
            Car car = cars.get(random.nextInt(cars.size()));
            Customer customer = customers.get(random.nextInt(customers.size()));
            Double discount = discounts.get(random.nextInt(discounts.size()));
            if (customer.getYoungDriver()) { //doesn't make much sense when randomly created but still
                discount += 0.05;
            }
            Sale sale = new Sale(car, customer, discount);
            saleRepository.save(sale);
            cars.remove(car);
        }
    }

    @Override
    public List<SaleDiscountView> getSalesWithDiscounts() {
        return saleRepository.findAll()
                .stream()
                .map(s -> new SaleDiscountView(
                        new PlainCarView(
                                s.getCar().getMake(),
                                s.getCar().getModel(),
                                s.getCar().getTravelledDistance()),
                        s.getCustomer().getName(),
                        s.getDiscount(),
                        s.getCar().getParts().stream().mapToDouble(c -> c.getPrice().doubleValue()).sum()))
                .collect(Collectors.toList());
    }
}
