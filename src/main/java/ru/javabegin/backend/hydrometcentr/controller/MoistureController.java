package ru.javabegin.backend.hydrometcentr.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

        // проверка на обязательные параметры
        if (moisture.getId() == null || moisture.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (moisture.getTitle() == null || moisture.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        // save работает как на добавление, так и на обновление
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
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

}
