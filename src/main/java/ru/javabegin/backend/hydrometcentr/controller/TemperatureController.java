package ru.javabegin.backend.hydrometcentr.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.hydrometcentr.entity.Temperature;
import ru.javabegin.backend.hydrometcentr.services.TemperatureServices;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    private final TemperatureServices temperatureServices;

    public TemperatureController(TemperatureServices services) {
        this.temperatureServices = services;
    }

    @PostMapping("/id")
    public ResponseEntity<Temperature> findById(@RequestBody Long id) {
        Temperature temperature = null;
        try {
            temperature = temperatureServices.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(temperature);
    }

    @PutMapping("/update")
    public ResponseEntity<Temperature> update(@RequestBody Temperature temperature) {

        // проверка на обязательные параметры
        if (temperature.getId() == null || temperature.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (temperature.getTitle() == null || temperature.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        
        // save работает как на добавление, так и на обновление
        temperatureServices.update(temperature);

        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            temperatureServices.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }
    
}
