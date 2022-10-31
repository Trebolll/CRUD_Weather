package ru.javabegin.backend.hydrometcentr.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.hydrometcentr.entity.Moisture;
import ru.javabegin.backend.hydrometcentr.entity.Moisture;
import ru.javabegin.backend.hydrometcentr.services.MoistureServices;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/moisture")
public class MoistureController {
    private final MoistureServices moistureServices;
    public MoistureController(MoistureServices services) {
        this.moistureServices = services;
    }

    @PostMapping("/id")
    public ResponseEntity<Moisture> findById(@RequestBody Long id) {
        Moisture moisture = null;
        try {
            moisture = moistureServices.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(moisture);
    }

    @PutMapping("/update")
    public ResponseEntity<Moisture> update(@RequestBody Moisture moisture) {


        if (moisture.getId() == null || moisture.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }


        if (moisture.getTitle() == null || moisture.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        moistureServices.update(moisture);

        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            moistureServices.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Moisture> add(@RequestBody Moisture moisture) {

        // проверка на обязательные параметры
        if (moisture.getId() != null && moisture.getId() != 0) {
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (moisture.getTitle() == null || moisture.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(moistureServices.add(moisture));

    }
}
