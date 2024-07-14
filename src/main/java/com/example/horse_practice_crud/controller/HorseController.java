package com.example.horse_practice_crud.controller;

import com.example.horse_practice_crud.exceptions.HorseNotFoundException;
import com.example.horse_practice_crud.model.Horse;
import com.example.horse_practice_crud.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/horses")
public class HorseController {

    @Autowired
    HorseService horseService;

    @PostMapping
    public ResponseEntity<Horse> createHorse(@RequestBody Horse horse) {
        Horse newHorse = horseService.create(horse);
        return new ResponseEntity<>(newHorse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Horse>> getAllHorses() {
        List<Horse> horses = horseService.findAllHorses();
        return new ResponseEntity<>(horses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horse> getHorseById(@PathVariable UUID id) {
        Horse horse;
        try {
            horse = horseService.findHorseById(id);
        } catch (HorseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(horse, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Horse> getHorseByName(@PathVariable String name) {
        Horse horse;
        try {
            horse = horseService.findHorseByName(name);
        } catch (HorseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(horse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horse> updateHorse(@RequestBody Horse horse, @PathVariable UUID id) {
        Horse newHorse;
        try {
            newHorse = horseService.updateHorse(horse, id);
        } catch (HorseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newHorse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Horse> deleteHorseById(@PathVariable UUID id) {
        Horse horse;
        try {
            horse = horseService.deleteHorse(id);
        } catch (HorseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(horse, HttpStatus.OK);
    }
}
