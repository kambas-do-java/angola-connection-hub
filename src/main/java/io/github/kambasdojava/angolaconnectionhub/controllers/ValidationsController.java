package io.github.kambasdojava.angolaconnectionhub.controllers;

import io.github.kambasdojava.angolaconnectionhub.dto.TaxData;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxDataRequest;
import io.github.kambasdojava.angolaconnectionhub.dto.ValidateTaxIdRequest;
import io.github.kambasdojava.angolaconnectionhub.dto.ValidateTaxIdResponse;
import io.github.kambasdojava.angolaconnectionhub.services.ValidationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/validations")
@RestController
@RequiredArgsConstructor
public class ValidationsController {

  private final ValidationsService validationsService;

  @PostMapping("/tax-id")
  public ResponseEntity<@NonNull ValidateTaxIdResponse> validateTaxId(@Valid @RequestBody ValidateTaxIdRequest request) {
    TaxDataRequest taxDataRequest = new TaxDataRequest(request.taxId());
    TaxData taxData = validationsService.getTaxData(taxDataRequest);
    ValidateTaxIdResponse response = new ValidateTaxIdResponse(
            taxData.name(),
            taxData.taxId(),
            taxData.type(),
            taxData.isActive()
    );
    return ResponseEntity.ok(response);
  }
}
