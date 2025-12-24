package io.github.kambasdojava.angolaconnectionhub.controllers.docs;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import java.lang.annotation.*;

@GlobalOpenAPiDocs.ACHRequestId
public interface GlobalOpenAPiDocs {

  @Parameters({
      @Parameter(name = "ACH-Request-ID", in = ParameterIn.HEADER)
  })
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Documented
  public @interface ACHRequestId {
  }
}
