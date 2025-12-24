package io.github.kambasdojava.angolaconnectionhub.services.impl;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.TimeoutError;
import io.github.kambasdojava.angolaconnectionhub.exceptions.ACHException;
import io.github.kambasdojava.angolaconnectionhub.exceptions.RequestTimeoutException;
import io.github.kambasdojava.angolaconnectionhub.exceptions.ResourceNotFoundException;
import io.github.kambasdojava.angolaconnectionhub.services.BrowserService;
import io.github.kambasdojava.angolaconnectionhub.services.ValidationsService;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxData;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxDataRequest;
import io.github.kambasdojava.angolaconnectionhub.utils.TaxUtils;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.github.kambasdojava.angolaconnectionhub.dto.TaxData.Type.E;
import static io.github.kambasdojava.angolaconnectionhub.dto.TaxData.Type.P;
import static io.github.kambasdojava.angolaconnectionhub.dto.TaxData.Type.U;

@Service
public class ValidationsServiceImpl implements ValidationsService {
  private final String taxPortal;
  private final BrowserService browser;

  public ValidationsServiceImpl(@Value("${ach.tax-portal.url}") String taxPortal, BrowserService browser) {
    this.taxPortal = taxPortal;
    this.browser = browser;
  }

  @Cacheable(value = "taxes", key = "#request.taxId")
  @Override
  public @NonNull TaxData getTaxData(@Valid @NonNull TaxDataRequest request) {
    try (var page = browser.createPage()) {

      page.navigate(taxPortal);

      page.fill("input[type='text']", request.taxId().trim().toUpperCase());
      page.click("button[type='submit']");

      page.waitForSelector("div[class='form-group'] div[class='col-sm-6']",
          new WaitForSelectorOptions().setTimeout(3000)
      );

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
          .map(s -> s.contains("Empresa") ? E : P)
          .orElse(U);
      var province = TaxUtils.getProvince(taxId);
      var isActive = "Activo".equalsIgnoreCase(data.get(3));

      return new TaxData(taxId, name, province, type, isActive);
    } catch (PlaywrightException e) {
      if (e instanceof TimeoutError) {
        throw new RequestTimeoutException("Cannot get response in time interval");
      }

      throw new ACHException(e.getMessage());
    }
  }
}
