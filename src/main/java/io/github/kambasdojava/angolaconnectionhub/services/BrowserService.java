package io.github.kambasdojava.angolaconnectionhub.services;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.jspecify.annotations.NonNull;

public interface BrowserService {
  @NonNull Page createPage() throws PlaywrightException;
}
