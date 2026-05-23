package io.github.kambasdojava.angolaconnectionhub.validations.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.kambasdojava.angolaconnectionhub.validations.service.PhoneValidationService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/validations")
@RequiredArgsConstructor
public class PhoneValidationController {
    private final PhoneValidationService    phoneValidationService;

    @GetMapping("/phone")
    public ResponseEntity<Object>   validatePhone(@RequestParam String number, @RequestParam List<String> types) {
        Object  response;

        response = phoneValidationService.validatePhone(number, types);
        return (ResponseEntity.ok(response));
    }
}
