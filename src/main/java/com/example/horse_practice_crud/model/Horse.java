package com.example.horse_practice_crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "horses")
@AllArgsConstructor
@NoArgsConstructor
public class Horse {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String color;

    private String gender;

    private String breed;

    private Integer age;

    private Boolean isHappy;

    public Horse(String name, String color, String gender, String breed, Integer age, Boolean isHappy) {
        this.name = name;
        this.color = color;
        this.gender = gender;
        this.breed = breed;
        this.age = age;
        this.isHappy = isHappy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsHappy() {
        return isHappy;
    }

    public void setIsHappy(Boolean isHappy) {
        this.isHappy = isHappy;
    }
}
