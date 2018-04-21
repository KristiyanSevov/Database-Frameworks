package app.retake.repositories;

import app.retake.domain.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{

    @Query(value = "SELECT a FROM Animal as a JOIN a.passport as p WHERE p.ownerPhoneNumber = :number")
    Set<Animal> findByOwnerPhoneNumber(@Param("number") String phoneNumber);
}
