package me.lucaspmntl.geofreight.repository;

import me.lucaspmntl.geofreight.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
