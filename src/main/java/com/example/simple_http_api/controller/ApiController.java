package com.example.simple_http_api.controller;

import com.example.simple_http_api.exception.InvalidInputException;
import com.example.simple_http_api.model.ErrorResponse;
import com.example.simple_http_api.model.MessageResponse;
import com.example.simple_http_api.service.ApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class ApiController {
    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public ResponseEntity<?> getMessage(@RequestParam(required = false) String name){
        try {
            String message = apiService.generateMessage(name);
            return ResponseEntity.ok(new MessageResponse(message));
        } catch (InvalidInputException e){
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid Input"));
        }
    }
}
