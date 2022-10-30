package ru.javabegin.backend.hydrometcentr.services;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.hydrometcentr.entity.Pressure;
import ru.javabegin.backend.hydrometcentr.repository.PressureRepository;
import ru.javabegin.backend.hydrometcentr.repository.TemperatureRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class PressureServices {


    private final PressureRepository repository;
    public PressureServices (PressureRepository repository){
        this.repository = repository;
        
    }
    public Pressure findById(Long id) {
        return repository.findById(id).get();
    }
    public Pressure add(Pressure town)  {
        return repository.save(town);
    }
    public Pressure update(Pressure town)  {
        return repository.save(town);
    }
    public void deleteById(Long id)  {
        repository.deleteById(id);
    }


}
