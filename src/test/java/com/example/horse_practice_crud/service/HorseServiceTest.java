package com.example.horse_practice_crud.service;

import com.example.horse_practice_crud.exceptions.HorseNotFoundException;
import com.example.horse_practice_crud.model.Horse;
import com.example.horse_practice_crud.repository.HorseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HorseServiceTest {

    private HorseService horseService;
    private HorseRepository mockHorseRepository;

    private final Horse input = new Horse(null, "Whisper", "black", "gelding", "mustang", 5, true);
    private final Horse input2 = new Horse(null, "Juliette", "gray", "mare", "arabian", 6, true);
    private final Horse recordWithId = new Horse(UUID.randomUUID(), "Whisper", "black", "gelding", "mustang", 5, true);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockHorseRepository = Mockito.mock(HorseRepository.class);
        horseService = new HorseService(mockHorseRepository);
    }

    @Test
    public void create_shouldReturnCreatedHorse() {
        Mockito.when(mockHorseRepository.save(Mockito.any())).thenReturn(recordWithId);
        Horse response = horseService.create(input);
        assertEquals(recordWithId, response);
    }

    @Test
    public void getAll_shouldReturnListOfHorses() {
        List<Horse> horses = new ArrayList<>();
        horses.add(input);
        horses.add(input2);
        Mockito.when(mockHorseRepository.findAll()).thenReturn(horses);
        List<Horse> response = horseService.getAll();
        assertEquals(horses, response);
    }

    @Test
    public void getById_shouldReturnHorse() {
        Mockito.when(mockHorseRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Horse response = horseService.getById(recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void getById_throwsExceptionWhenHorseNotFound() {
        Mockito.when(mockHorseRepository.findById(id)).thenReturn(Optional.empty());
        HorseNotFoundException exception = assertThrows(HorseNotFoundException.class, () -> horseService.getById(id));
        assertEquals("A horse with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void getByName_shouldReturnListOfHorses() {
        Mockito.when(mockHorseRepository.findByName(recordWithId.getName())).thenReturn(List.of(recordWithId));
        List<Horse> response = horseService.getByName(recordWithId.getName());
        assertEquals(List.of(recordWithId), response);
    }

    @Test
    public void update_shouldReturnUpdatedHorse() {
        Mockito.when(mockHorseRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockHorseRepository.save(Mockito.any())).thenReturn(recordWithId);
        Horse response = horseService.update(input2, recordWithId.getId());
        assertEquals(recordWithId, response);
    }


}