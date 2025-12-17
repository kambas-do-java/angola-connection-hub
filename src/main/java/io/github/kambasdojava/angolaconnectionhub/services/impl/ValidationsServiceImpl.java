package io.github.kambasdojava.angolaconnectionhub.services.impl;

import com.microsoft.playwright.Browser.NewPageOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.TimeoutError;
import io.github.kambasdojava.angolaconnectionhub.exceptions.ResourceNotFoundException;
import io.github.kambasdojava.angolaconnectionhub.services.ValidationsService;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxData;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxDataRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ValidationsServiceImpl implements ValidationsService {
  private final String taxPortal;

  public ValidationsServiceImpl(@Value("${ach.tax-portal.url}") String taxPortal) {
    this.taxPortal = taxPortal;
  }

  @Cacheable(value = "taxes", key = "#request.taxId")
  @Override
  public @NonNull TaxData getTaxData(@NonNull TaxDataRequest request) {
    try (Playwright playwright = Playwright.create();
         var browser = playwright.firefox().launch(new LaunchOptions().setHeadless(true))) {
      var page = browser.newPage(new NewPageOptions().setIgnoreHTTPSErrors(true));

      page.navigate(taxPortal);

      page.fill("input[type='text']", request.taxId().trim().toUpperCase());
      page.click("button[type='submit']");
      try {
        page.waitForSelector("div[class='form-group'] div[class='col-sm-6']",
            new WaitForSelectorOptions().setTimeout(3000)
        );
      } catch (TimeoutError e) {
        throw new ResourceNotFoundException("NIF %s not found".formatted(request.taxId()));
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
      var type = data.get(2);
      var isActive = "Activo".equals(data.get(3));

      return new TaxData(name, taxId, type, isActive);
    }
  }
}
