package io.github.kambasdojava.angolaconnectionhub.services.impl;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.TimeoutError;
import io.github.kambasdojava.angolaconnectionhub.exceptions.ConnectionTimeoutException;
import io.github.kambasdojava.angolaconnectionhub.exceptions.ResourceNotFoundException;
import io.github.kambasdojava.angolaconnectionhub.services.BrowserService;
import io.github.kambasdojava.angolaconnectionhub.services.ValidationsService;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxData;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxDataRequest;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationsServiceImpl implements ValidationsService {
  private final String taxPortal;
  private final BrowserService service;

  public ValidationsServiceImpl(@Value("${ach.tax-portal.url}") String taxPortal,
                                BrowserService service) {
    this.taxPortal = taxPortal;
    this.service = service;
  }

  @Cacheable(value = "taxes", key = "#request.taxId")
  @Override
  public @NonNull TaxData getTaxData(@Valid @NonNull TaxDataRequest request) {
    try (var page = service.createPage()) {

      page.navigate(taxPortal);

      page.fill("input[type='text']", request.taxId().trim().toUpperCase());
      page.click("button[type='submit']");
      try {
        page.waitForSelector("div[class='form-group'] div[class='col-sm-6']",
            new WaitForSelectorOptions().setTimeout(3000)
        );
      } catch (TimeoutError e) {
        throw new ConnectionTimeoutException("NIF %s not found".formatted(request.taxId()));
      }

      var data = page.querySelectorAll("div[class='form-group'] div[class='col-sm-6']")
          .stream()
          .map(ElementHandle::innerText)
          .limit(4)
          .toList();

      if (data.size() < 4) {
        throw new ResourceNotFoundException("NIF %s not found".formatted(request.taxId()));
      }

      var taxId = data.getFirst();
      var name = data.get(1);
      var type = Optional.ofNullable(data.get(2))
          .map(s -> s.contains("Empresa") ? "Colectivo" : "Particular")
          .orElse("Desconhecido");
      var isActive = "Activo".equalsIgnoreCase(data.get(3));

      return new TaxData(name, taxId, type, isActive);
    }
  }
}
