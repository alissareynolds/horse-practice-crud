package com.example.horse_practice_crud.controller;

import com.example.horse_practice_crud.model.Horse;
import com.example.horse_practice_crud.service.HorseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HorseControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HorseService mockHorseService;

    private final Horse horse = new Horse(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"), "Whisper", "black", "gelding", "mustang", 5, true);

    @Test
    public void createHorse() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/horses")
                .content(asJsonString(new Horse(null, "Whisper", "black", "gelding", "mustang", 5, true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllHorses() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/horses")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
