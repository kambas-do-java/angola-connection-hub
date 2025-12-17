package io.github.kambasdojava.angolaconnectionhub.controllers;

import io.github.kambasdojava.angolaconnectionhub.dto.ValidateTaxIdRequest;
import io.github.kambasdojava.angolaconnectionhub.dto.ValidateTaxIdResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/validations")
@RestController
@RequiredArgsConstructor
public class ValidationsController {

  @PostMapping("/tax-id")
  public ResponseEntity<@NonNull ValidateTaxIdResponse> validateTaxId(@Valid ValidateTaxIdRequest request) {
    return null;
  }
}
