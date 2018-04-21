package com.masdefect.repository;

import com.masdefect.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    Person findByName(String person);
}
