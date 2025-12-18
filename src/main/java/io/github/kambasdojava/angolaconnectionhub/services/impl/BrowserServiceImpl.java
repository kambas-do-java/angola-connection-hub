package io.github.kambasdojava.angolaconnectionhub.services.impl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewPageOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import io.github.kambasdojava.angolaconnectionhub.services.BrowserService;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BrowserServiceImpl implements BrowserService {
  @Override
  public @NonNull Page createPage() throws PlaywrightException {
    try (Playwright playwright = Playwright.create()) {
      try (Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(true))) {
        return browser.newPage(new NewPageOptions().setIgnoreHTTPSErrors(true));
      }
    } catch (Exception e) {
      // Aqui você pode capturar exceções caso ocorra algum erro na criação ou no fechamento
      throw new PlaywrightException("Erro ao inicializar o Playwright ou o navegador", e);
    }
  }
}
