package ru.javabegin.backend.hydrometcentr.services;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.hydrometcentr.entity.Town;
import ru.javabegin.backend.hydrometcentr.repository.TownRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class TownServices {

    private final TownRepository repository;

    public TownServices (TownRepository repository){
        this.repository = repository;
    }

    public Town findById(Long id) {
        return repository.findById(id).get();


    }




}
