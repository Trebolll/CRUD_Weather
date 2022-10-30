package ru.javabegin.backend.hydrometcentr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.backend.hydrometcentr.entity.Town;
import ru.javabegin.backend.hydrometcentr.repository.TownRepository;
import ru.javabegin.backend.hydrometcentr.services.TownServices;

import javax.transaction.Transactional;
@RestController
@RequestMapping("/town")
public class TownController  {
    private final TownServices services;

    public TownController (TownServices services){
        this.services = services;
    }

@GetMapping("/id")
    public Town findById() {
        return services.findById(13L);


    }

}
