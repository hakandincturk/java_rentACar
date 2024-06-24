package hako.rentACar.dto.brand.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBrandWithModelsResponse {
  private int id;
  private String name;
  private List<GetAllBrandWithModelsResponseModels> models;
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class GetAllBrandWithModelsResponseModels {
  private int id;
  private String name;
}