package ru.javabegin.backend.hydrometcentr.services;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.hydrometcentr.entity.Fallout;
import ru.javabegin.backend.hydrometcentr.repository.FalloutRepository;
import ru.javabegin.backend.hydrometcentr.repository.MoistureRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class FalloutServices {
    private final FalloutRepository repository;
    public FalloutServices (FalloutRepository repository){
        this.repository = repository;

    }
    public Fallout findById(Long id) {
        return repository.findById(id).get();
    }
    public Fallout add(Fallout town)  {
        return repository.save(town);
    }
    public Fallout update(Fallout town)  {
        return repository.save(town);
    }
    public void deleteById(Long id)  {
        repository.deleteById(id);
    }

}
