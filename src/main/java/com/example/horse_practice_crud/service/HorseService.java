package com.example.horse_practice_crud.service;

import com.example.horse_practice_crud.exceptions.HorseNotFoundException;
import com.example.horse_practice_crud.model.Horse;
import com.example.horse_practice_crud.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HorseService {

    @Autowired
    HorseRepository horseRepository;

    public Horse create(Horse horse) {
        Horse newHorse = new Horse(horse.getName(), horse.getColor(), horse.getGender(), horse.getBreed(), horse.getAge(), horse.getIsHappy());
        return horseRepository.save(newHorse);
    }

    public List<Horse> findAllHorses() {
        return horseRepository.findAll();
    }

    public Horse findHorseById(UUID id) {
        Optional<Horse> horse = horseRepository.findById(id);
        if (horse.isEmpty()) {
            throw new HorseNotFoundException("A horse with that id was not found.");
        }
        return horse.get();
    }

    public Horse findHorseByName(String name) {
        Optional<Horse> horse = horseRepository.findByName(name);
        if (horse.isEmpty()) {
            throw new HorseNotFoundException("A horse with that name was not found.");
        }
        return horse.get();
    }

    public Horse updateHorse(Horse horse, UUID id) {
        Optional<Horse> oldHorse = horseRepository.findById(id);
        if (oldHorse.isEmpty()) {
            throw new HorseNotFoundException("A horse with that id was not found.");
        }
        Horse newHorse = new Horse(id, horse.getName(), horse.getColor(), horse.getGender(), horse.getBreed(), horse.getAge(), horse.getIsHappy());
        return horseRepository.save(newHorse);
    }

    public Horse deleteHorse(UUID id) {
        Optional<Horse> horse = horseRepository.findById(id);
        if (horse.isEmpty()) {
            throw new HorseNotFoundException("A horse with this id was not found");
        }
        horseRepository.delete(horse.get());
        return horse.get();
    }
}
