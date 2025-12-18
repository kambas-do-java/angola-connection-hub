package io.github.kambasdojava.angolaconnectionhub.services;

import io.github.kambasdojava.angolaconnectionhub.dto.TaxData;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxDataRequest;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;

public interface ValidationsService {
  @NonNull TaxData getTaxData(@Valid @NonNull TaxDataRequest request);
}
