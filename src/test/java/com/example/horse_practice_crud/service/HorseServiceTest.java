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

    @Test
    public void update_throwsExceptionWhenHorseWasNotFound() {
        Mockito.when(mockHorseRepository.findById(id)).thenReturn(Optional.empty());
        HorseNotFoundException exception = assertThrows(HorseNotFoundException.class, () -> horseService.update(input, id));
        assertEquals("A horse with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_throwsExceptionWhenHorseWasNotFound() {
        Mockito.when(mockHorseRepository.findById(id)).thenReturn(Optional.empty());
        HorseNotFoundException exception = assertThrows(HorseNotFoundException.class, () -> horseService.patch(input, id));
        assertEquals("A horse with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_shouldReturnUpdatedName() {
     Horse input = new Horse();
     input.setName("Star");
     Mockito.when(mockHorseRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
     Mockito.when(mockHorseRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
     Horse response = horseService.patch(input, recordWithId.getId());
     assertEquals(recordWithId.getId(), response.getId());
     assertEquals("Star", response.getName());
     assertEquals("black", response.getColor());
     assertEquals("gelding", response.getGender());
     assertEquals("mustang", response.getBreed());
     assertEquals(5, response.getAge());
     assertEquals(true, response.getIsHappy());
    }

    @Test
    public void patch_shouldReturnUpdatedColor() {
        Horse input = new Horse();
        input.setColor("roan");
        Mockito.when(mockHorseRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockHorseRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Horse response = horseService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Whisper", response.getName());
        assertEquals("roan", response.getColor());
        assertEquals("gelding", response.getGender());
        assertEquals("mustang", response.getBreed());
        assertEquals(5, response.getAge());
        assertEquals(true, response.getIsHappy());
    }

    @Test
    public void patch_shouldReturnUpdatedGender() {
        Horse input = new Horse();
        input.setGender("mare");
        Mockito.when(mockHorseRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockHorseRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Horse response = horseService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Whisper", response.getName());
        assertEquals("black", response.getColor());
        assertEquals("mare", response.getGender());
        assertEquals("mustang", response.getBreed());
        assertEquals(5, response.getAge());
        assertEquals(true, response.getIsHappy());
    }

    @Test
    public void patch_shouldReturnUpdatedBreed() {
        Horse input = new Horse();
        input.setBreed("quarter horse");
        Mockito.when(mockHorseRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockHorseRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Horse response = horseService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Whisper", response.getName());
        assertEquals("black", response.getColor());
        assertEquals("gelding", response.getGender());
        assertEquals("quarter horse", response.getBreed());
        assertEquals(5, response.getAge());
        assertEquals(true, response.getIsHappy());
    }

    @Test
    public void patch_shouldReturnUpdatedAge() {
        Horse input = new Horse();
        input.setAge(7);
        Mockito.when(mockHorseRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockHorseRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Horse response = horseService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Whisper", response.getName());
        assertEquals("black", response.getColor());
        assertEquals("gelding", response.getGender());
        assertEquals("mustang", response.getBreed());
        assertEquals(7, response.getAge());
        assertEquals(true, response.getIsHappy());
    }

    @Test
    public void patch_shouldReturnUpdatedIsHappy() {
        Horse input = new Horse();
        input.setIsHappy(false);
        Mockito.when(mockHorseRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockHorseRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Horse response = horseService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Whisper", response.getName());
        assertEquals("black", response.getColor());
        assertEquals("gelding", response.getGender());
        assertEquals("mustang", response.getBreed());
        assertEquals(5, response.getAge());
        assertEquals(false, response.getIsHappy());
    }

    @Test
    public void delete_callsRepositoryDeleteMethod() {
        horseService.delete(id);
        Mockito.verify(mockHorseRepository).deleteById(id);
    }
}