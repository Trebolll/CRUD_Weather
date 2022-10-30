package ru.javabegin.backend.hydrometcentr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.backend.hydrometcentr.entity.Temperature;
@Repository
public interface TemperatureRepository extends JpaRepository<Temperature,Long> {
}
