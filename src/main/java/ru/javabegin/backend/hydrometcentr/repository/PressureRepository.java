package ru.javabegin.backend.hydrometcentr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.backend.hydrometcentr.entity.Pressure;
@Repository
public interface PressureRepository extends JpaRepository<Pressure,Long> {
}
