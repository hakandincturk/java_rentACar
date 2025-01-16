package hako.rentACar.dto.car.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {
  private int id;
  private String plate;
  private String modelName;
  private String state;
  private int km;
  private double dailyPrice;
}
