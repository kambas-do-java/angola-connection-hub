package io.github.kambasdojava.angolaconnectionhub.services;

import com.microsoft.playwright.Page;
import org.jspecify.annotations.NonNull;

public interface BrowserService {
  @NonNull Page createPage();
}
