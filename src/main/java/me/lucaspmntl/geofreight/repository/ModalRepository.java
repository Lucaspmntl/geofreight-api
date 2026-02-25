package me.lucaspmntl.geofreight.repository;

import me.lucaspmntl.geofreight.model.Modal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalRepository extends JpaRepository<Modal, Long> {
}
