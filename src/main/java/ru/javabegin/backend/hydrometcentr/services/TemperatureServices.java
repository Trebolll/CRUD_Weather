package ru.javabegin.backend.hydrometcentr.services;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.hydrometcentr.entity.Temperature;
import ru.javabegin.backend.hydrometcentr.entity.Town;
import ru.javabegin.backend.hydrometcentr.repository.TemperatureRepository;
import ru.javabegin.backend.hydrometcentr.repository.TownRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class TemperatureServices {

    private final TemperatureRepository repository;
    public TemperatureServices (TemperatureRepository repository){
        this.repository = repository;
    }

    public Temperature findById(Long id) {
        return repository.findById(id).get();
    }
    public Temperature add(Temperature town)  {
        return repository.save(town);
    }
    public Temperature update(Temperature town)  {
        return repository.save(town);
    }
    public void deleteById(Long id)  {
        repository.deleteById(id);
    }


}
