package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByBrandAndModelAndYearOfProduction(String brand, String model, Integer year);
}
