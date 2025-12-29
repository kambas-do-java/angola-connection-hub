package io.github.kambasdojava.angolaconnectionhub.controllers.docs;

import io.github.kambasdojava.angolaconnectionhub.dto.ValidateTaxIdRequest;
import io.github.kambasdojava.angolaconnectionhub.dto.ValidateTaxIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Validations API", description = "A set of validations API")
public interface ValidationsControllerDocs extends GlobalOpenAPiDocs {

  @Operation(operationId = "validateTaxId", description = "Validate tax id")
  @ApiResponse(
      responseCode = "200",
      description = "Returns Valid TaxData",
      content = @Content(
          schema = @Schema(implementation = ValidateTaxIdResponse.class),
          mediaType = APPLICATION_JSON_VALUE
      )
  )
  ResponseEntity<@NonNull ValidateTaxIdResponse> validateTaxId(
      @RequestBody ValidateTaxIdRequest request
  );
}
