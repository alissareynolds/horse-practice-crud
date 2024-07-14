package com.example.horse_practice_crud.exceptions;

public class HorseNotFoundException extends RuntimeException{
    public HorseNotFoundException(String message) {
        super(message);
    }
}
