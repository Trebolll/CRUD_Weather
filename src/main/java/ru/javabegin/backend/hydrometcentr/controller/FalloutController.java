package ru.javabegin.backend.hydrometcentr.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.hydrometcentr.entity.Fallout;
import ru.javabegin.backend.hydrometcentr.entity.Fallout;
import ru.javabegin.backend.hydrometcentr.services.FalloutServices;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/fallout")
public class FalloutController {

    private final FalloutServices falloutServices;

    public FalloutController(FalloutServices services) {
        this.falloutServices = services;
    }

    @PostMapping("/id")
    public ResponseEntity<Fallout> findById(@RequestBody Long id) {
        Fallout fallout = null;
        try {
            fallout = falloutServices.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(fallout);
    }

    @PutMapping("/update")
    public ResponseEntity<Fallout> update(@RequestBody Fallout fallout) {

        // проверка на обязательные параметры
        if (fallout.getId() == null || fallout.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (fallout.getForm() == null || fallout.getForm().trim().length() == 0) {
            return new ResponseEntity("missed param: form", HttpStatus.NOT_ACCEPTABLE);
        }

        // save работает как на добавление, так и на обновление
        falloutServices.update(fallout);

        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            falloutServices.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

    @PostMapping("/add")
    public ResponseEntity<Fallout> add(@RequestBody Fallout fallout) {

        // проверка на обязательные параметры
        if (fallout.getId() != null && fallout.getId() != 0) {
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (fallout.getForm() == null || fallout.getForm().trim().length() == 0) {
            return new ResponseEntity("missed param: form", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(falloutServices.add(fallout));

    }
    
    
}
