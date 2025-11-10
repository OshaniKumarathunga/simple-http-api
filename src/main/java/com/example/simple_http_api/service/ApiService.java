package com.example.simple_http_api.service;

import com.example.simple_http_api.exception.InvalidInputException;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    public String generateMessage(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Invalid Input");
        }
        char first = Character.toUpperCase(name.charAt(0));
        if (first < 'A' || first > 'Z') {
            throw new InvalidInputException("Invalid Input");
        }
        if (first > 'M') {
            throw new InvalidInputException("Invalid Input");
        }
        String formatted = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        return "Hello " + formatted;
    }


}