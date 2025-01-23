package hako.rentACar.dto.car.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
  private String plate;
  private int modelId;
  private int km;
  private int modelYear;
  private double dailyPrice;
  private int locationId;
}
