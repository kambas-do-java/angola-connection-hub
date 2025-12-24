package io.github.kambasdojava.angolaconnectionhub.services.impl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewPageOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import io.github.kambasdojava.angolaconnectionhub.services.BrowserService;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BrowserServiceImpl implements BrowserService {
  private final Playwright playwright;
  private final Browser browser;

  public BrowserServiceImpl() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch(new LaunchOptions().setHeadless(true));
  }

  @Override
  public @NonNull Page createPage() {
    return browser.newPage(new NewPageOptions().setIgnoreHTTPSErrors(true));
  }

  @PreDestroy
  public void cleanup() {
    try {
      playwright.close();
      browser.close();
    } catch (PlaywrightException e) {
      log.error("Occurred an error when trying closing Playwright", e);
    }
  }
}
