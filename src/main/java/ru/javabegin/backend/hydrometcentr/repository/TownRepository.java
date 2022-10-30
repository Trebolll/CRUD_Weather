package ru.javabegin.backend.hydrometcentr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.backend.hydrometcentr.entity.Town;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town,Long> {

}
