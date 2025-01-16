package hako.rentACar.dto.location.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationRequest {

  @NotNull
  @NotBlank
  @Size(min = 2, max = 20)
  private String name;

  @NotNull
  @NotBlank
  @Size(min = 2, max = 20)
  private String adress;
}