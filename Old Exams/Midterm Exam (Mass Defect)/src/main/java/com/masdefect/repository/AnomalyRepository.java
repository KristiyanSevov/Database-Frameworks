package com.masdefect.repository;

import com.masdefect.domain.entities.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Long>{
}
