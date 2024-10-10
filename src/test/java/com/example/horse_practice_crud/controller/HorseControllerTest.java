package com.example.horse_practice_crud.controller;

import com.example.horse_practice_crud.exceptions.HorseNotFoundException;
import com.example.horse_practice_crud.model.Horse;
import com.example.horse_practice_crud.service.HorseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HorseControllerTest {

    private HorseController horseController;
    private HorseService mockHorseService;

    private final Horse input = new Horse(null, "Whisper", "black", "gelding", "mustang", 5, true);
    private final Horse input2 = new Horse(null, "Juliette", "gray", "mare", "arabian", 6, true);
    private final Horse recordWithId = new Horse(UUID.randomUUID(), "Whisper", "black", "gelding", "mustang", 5, true);
    private final Horse recordWithId2 = new Horse(recordWithId.getId(), "Juliette", "gray", "mare", "arabian", 6, true);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockHorseService = Mockito.mock(HorseService.class);
        horseController = new HorseController(mockHorseService);
    }

    @Test
    public void createHorse_shouldReturnHorseAndCREATEDHttpStatus() {
        Mockito.when(mockHorseService.create(Mockito.any())).thenReturn(recordWithId);
        ResponseEntity<Horse> response = horseController.createHorse(input);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getAllHorses_shouldReturnListOfHorsesAndOKHttpStatus() {
        List<Horse> horses = new ArrayList<>();
        horses.add(input);
        horses.add(input2);
        Mockito.when(mockHorseService.getAll()).thenReturn(horses);
        ResponseEntity<List<Horse>> response = horseController.getAllHorses();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(horses, response.getBody());
    }

    @Test
    public void getHorseById_shouldReturnHorseAndOKHttpStatus() {
        Mockito.when(mockHorseService.getById(recordWithId.getId())).thenReturn(recordWithId);
        ResponseEntity<Horse> response = horseController.getHorseById(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getHorseById_shouldReturn404WhenHorseNotFound() {
        Mockito.when(mockHorseService.getById(id)).thenThrow(new HorseNotFoundException("A horse with id: " + id + " was not found."));
        ResponseEntity<Horse> response = horseController.getHorseById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getHorseByName_shouldReturnListOfHorsesAndOKHttpStatus() {
        Mockito.when(mockHorseService.getByName(recordWithId.getName())).thenReturn(List.of(recordWithId));
        ResponseEntity<List<Horse>> response = horseController.getHorseByName(recordWithId.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(recordWithId), response.getBody());
    }

    @Test
    public void updateHorse_shouldReturnHorseAndOKHttpStatus() {
        Mockito.when(mockHorseService.update(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Horse> response = horseController.updateHorse(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void updateHorse_shouldReturn404WhenHorseNotFound() {
        Mockito.when(mockHorseService.update(input, id)).thenThrow(new HorseNotFoundException("A horse with id: " + id + " was not found."));
        ResponseEntity<Horse> response = horseController.updateHorse(input, id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void patchHorse_shouldReturnHorseAndOKHttpStatus() {
        Mockito.when(mockHorseService.patch(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Horse> response = horseController.patchHorse(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void patchHorse_shouldReturn404WhenHorseNotFound() {
        Mockito.when(mockHorseService.patch(input, id)).thenThrow(new HorseNotFoundException("A horse with id: " + id + " was not found."));
        ResponseEntity<Horse> response = horseController.patchHorse(input, id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteHorse_shouldReturnOKHttpStatus() {
        ResponseEntity<Horse> response = horseController.deleteHorseById(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}