package me.lucaspmntl.geofreight.repository;

import me.lucaspmntl.geofreight.model.FreightTariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightTariffRepository extends JpaRepository<FreightTariff, Long> {
}
