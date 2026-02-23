package me.lucaspmntl.geofreight.repository;

import me.lucaspmntl.geofreight.model.FreightCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightCalculationRepository extends JpaRepository<FreightCalculation, Long> {
}
