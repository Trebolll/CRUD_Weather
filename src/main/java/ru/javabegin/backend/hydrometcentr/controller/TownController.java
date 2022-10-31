package ru.javabegin.backend.hydrometcentr.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.hydrometcentr.entity.Town;
import ru.javabegin.backend.hydrometcentr.services.TownServices;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/town")
public class TownController {
    private final TownServices townServices;

    public TownController(TownServices services) {
        this.townServices = services;
    }

    @PostMapping("/id")
    public ResponseEntity<Town> findById(@RequestBody Long id) {
        Town town = null;
        try {
            town = townServices.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(town);
    }

    // обновление
    @PutMapping("/update")
    public ResponseEntity<Town> update(@RequestBody Town town) {

        // проверка на обязательные параметры
        if (town.getId() == null || town.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (town.getName() == null || town.getName().trim().length() == 0) {
            return new ResponseEntity("missed param: name", HttpStatus.NOT_ACCEPTABLE);
        }


        // save работает как на добавление, так и на обновление
        townServices.update(town);

        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            townServices.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

    @PostMapping("/add")
    public ResponseEntity<Town> add(@RequestBody Town town) {

        // проверка на обязательные параметры
        if (town.getId() != null && town.getId() != 0) {
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (town.getName() == null || town.getName().trim().length() == 0) {
            return new ResponseEntity("missed param: name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(townServices.add(town));

    }
}
