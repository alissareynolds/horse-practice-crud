package com.example.horse_practice_crud.service;

import com.example.horse_practice_crud.exceptions.HorseNotFoundException;
import com.example.horse_practice_crud.model.Horse;
import com.example.horse_practice_crud.repository.HorseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HorseService {

    private final HorseRepository horseRepository;

    public HorseService(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    public Horse create(Horse horse) {
        return horseRepository.save(horse);
    }

    public List<Horse> getAll() {
        return horseRepository.findAll();
    }

    public Horse getById(UUID id) {
        Optional<Horse> optionalHorse = horseRepository.findById(id);
        if (optionalHorse.isEmpty()) {
            throw new HorseNotFoundException("A horse with id: " + id + " was not found.");
        }
        return optionalHorse.get();
    }

    public List<Horse> getByName(String name) {
        return horseRepository.findByName(name);
    }

    public Horse update(Horse horse, UUID id) {
        Optional<Horse> optionalHorse = horseRepository.findById(id);
        if (optionalHorse.isEmpty()) {
            throw new HorseNotFoundException("A horse with id: " + id + " was not found.");
        }
        horse.setId(id);
        return horseRepository.save(horse);
    }

    public Horse patch(Horse horse, UUID id) {
        Optional<Horse> optionalHorse = horseRepository.findById(id);
        if (optionalHorse.isEmpty()) {
            throw new HorseNotFoundException("A horse with id: " + id + " was not found.");
        }
        Horse updatedHorse = optionalHorse.get();
        if (horse.getName() != null) {
            updatedHorse.setName(horse.getName());
        }
        if (horse.getColor() != null) {
            updatedHorse.setColor(horse.getColor());
        }
        if (horse.getGender() != null) {
            updatedHorse.setGender(horse.getGender());
        }
        if (horse.getBreed() != null) {
            updatedHorse.setBreed(horse.getBreed());
        }
        if (horse.getAge() != null) {
            updatedHorse.setAge(horse.getAge());
        }
        if (horse.getIsHappy() != null) {
            updatedHorse.setIsHappy(horse.getIsHappy());
        }
        return horseRepository.save(updatedHorse);
    }

    public void delete(UUID id) {
        horseRepository.deleteById(id);
    }
}
