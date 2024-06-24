package hako.rentACar.business.abstracts;

import java.util.List;

import hako.rentACar.dto.brand.requests.UpdateBrandRequest;
import hako.rentACar.dto.model.requests.CreateModelRequest;
import hako.rentACar.dto.model.responses.GetAllModelsResponse;
import hako.rentACar.dto.model.responses.GetModelResponse;

public interface ModelService {
  GetModelResponse getOne(int modelId);
  List<GetAllModelsResponse> getAll();
  void add(CreateModelRequest request);
  void update(UpdateBrandRequest request);
  void delete(int id);
}
