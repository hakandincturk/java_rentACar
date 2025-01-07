package hako.rentACar.dto.brand.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdBrandResponse {
  private int id;
  private String name;
}
