package hako.rentACar.business.abstracts;

import java.util.List;

import hako.rentACar.dto.model.requests.CreateModelRequest;
import hako.rentACar.dto.model.requests.UpdateModelRequest;
import hako.rentACar.dto.model.responses.GetAllModelsResponse;
import hako.rentACar.dto.model.responses.GetModelResponse;

public interface ModelService {
  GetModelResponse getOne(int modelId);
  List<GetAllModelsResponse> getAll();
  void add(CreateModelRequest request);
  void update(int modelId, UpdateModelRequest updateModelRequest);
  void delete(int id);
}
