package hako.rentACar.dto.model.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelRequest {

  @NotNull
  @NotBlank
  @Size(min = 2, max = 20)
  private String name;

  @NotNull
  private int brandId;
}
