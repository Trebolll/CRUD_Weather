package ru.javabegin.backend.hydrometcentr.services;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.hydrometcentr.entity.Moisture;
import ru.javabegin.backend.hydrometcentr.repository.MoistureRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class MoistureServices {
    private final MoistureRepository repository;
    public MoistureServices (MoistureRepository repository){
        this.repository = repository;

    }
    public Moisture findById(Long id) {
        return repository.findById(id).get();
    }
    public Moisture add(Moisture town)  {
        return repository.save(town);
    }
    public Moisture update(Moisture town)  {
        return repository.save(town);
    }
    public void deleteById(Long id)  {
        repository.deleteById(id);
    }
    
}
