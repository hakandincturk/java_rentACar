package hako.rentACar.business.abstracts;

import java.util.List;

import hako.rentACar.dto.model.responses.GetAllModelsResponse;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
}
