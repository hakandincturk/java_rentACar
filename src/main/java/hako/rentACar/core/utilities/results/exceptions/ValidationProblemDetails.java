package hako.rentACar.core.utilities.results.exceptions;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationProblemDetails {
  private Map<String, String> validationErrors;

}
