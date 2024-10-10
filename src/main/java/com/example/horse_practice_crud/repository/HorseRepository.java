package com.example.horse_practice_crud.repository;

import com.example.horse_practice_crud.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HorseRepository extends JpaRepository<Horse, UUID> {
    List<Horse> findByName(String name);
}
