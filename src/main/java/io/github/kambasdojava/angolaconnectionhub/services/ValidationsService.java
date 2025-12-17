package io.github.kambasdojava.angolaconnectionhub.services;

import io.github.kambasdojava.angolaconnectionhub.dto.TaxData;
import io.github.kambasdojava.angolaconnectionhub.dto.TaxDataRequest;
import org.jspecify.annotations.NonNull;

public interface ValidationsService {
  @NonNull TaxData getTaxData(@NonNull TaxDataRequest request);
}
