package ru.javabegin.backend.hydrometcentr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.backend.hydrometcentr.entity.Fallout;
@Repository
public interface FalloutRepository extends JpaRepository<Fallout,Long> {
}
