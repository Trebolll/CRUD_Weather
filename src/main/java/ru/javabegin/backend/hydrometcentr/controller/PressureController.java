package ru.javabegin.backend.hydrometcentr.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.hydrometcentr.entity.Pressure;
import ru.javabegin.backend.hydrometcentr.entity.Pressure;
import ru.javabegin.backend.hydrometcentr.services.PressureServices;

import java.util.NoSuchElementException;

    @RestController
    @RequestMapping("/pressure")
    public class PressureController{

        private final PressureServices pressureServices;

        public PressureController(PressureServices services) {
            this.pressureServices = services;
        }

        @PostMapping("/id")
        public ResponseEntity<Pressure> findById(@RequestBody Long id) {
            Pressure pressure = null;
            try {
                pressure = pressureServices.findById(id);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
            }
            return ResponseEntity.ok(pressure);
        }

        @PutMapping("/update")
        public ResponseEntity<Pressure> update(@RequestBody Pressure pressure) {

            // проверка на обязательные параметры
            if (pressure.getId() == null || pressure.getId() == 0) {
                return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
            }

            // если передали пустое значение title
            if (pressure.getTitle() == null || pressure.getTitle().trim().length() == 0) {
                return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
            }

            // save работает как на добавление, так и на обновление
            pressureServices.update(pressure);

            return new ResponseEntity(HttpStatus.OK);
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity delete(@PathVariable("id") Long id) {

            try {
                pressureServices.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                e.printStackTrace();
                return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
        }

        @PostMapping("/add")
        public ResponseEntity<Pressure> add(@RequestBody Pressure pressure) {

            // проверка на обязательные параметры
            if (pressure.getId() != null && pressure.getId() != 0) {
                // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
                return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
            }

            // если передали пустое значение title
            if (pressure.getTitle() == null || pressure.getTitle().trim().length() == 0) {
                return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
            }

            return ResponseEntity.ok(pressureServices.add(pressure));

        }

    }
