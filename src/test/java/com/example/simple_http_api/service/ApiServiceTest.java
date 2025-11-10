package com.example.simple_http_api.service;

import com.example.simple_http_api.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApiServiceTest {
    private ApiService apiService;

    @BeforeEach
    void setUp(){
        apiService = new ApiService();
    }

    @Test
    @DisplayName("should return greeting when name starts with A‑M")
    void shouldReturnHelloForAtoM(){
        String input = "alice";
        String result = apiService.generateMessage(input);
        assertEquals("Hello Alice", result);
    }

    @Test
    @DisplayName("should throw InvalidInputException when name is null")
    void shouldThrowErrorForEmptyName() {
        String input = null;
        InvalidInputException ex = assertThrows(InvalidInputException.class,
                () -> apiService.generateMessage(input));
        assertEquals("Invalid Input", ex.getMessage());
    }

    @Test
    @DisplayName("should throw InvalidInputException when name is empty or blank")
    void shouldThrowErrorForMissingName() {
        String input1 = "";
        String input2 = "   ";
        InvalidInputException ex1 = assertThrows(
                InvalidInputException.class,
                () -> apiService.generateMessage(input1)
        );
        assertEquals("Invalid Input", ex1.getMessage());

        InvalidInputException ex2 = assertThrows(
                InvalidInputException.class,
                () -> apiService.generateMessage(input2)
        );
        assertEquals("Invalid Input", ex2.getMessage());
    }

    @Test
    @DisplayName("should throw InvalidInputException when first character is non‑alphabetic")
    void shouldThrowWhenFirstCharIsNotLetter() {
        String input = "1alice";
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> apiService.generateMessage(input)
        );
        assertEquals("Invalid Input", ex.getMessage());
    }
}

