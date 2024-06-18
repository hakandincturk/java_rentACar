package hako.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hako.rentACar.business.abstracts.ModelService;
import hako.rentACar.core.utilities.mappers.ModelMapperService;
import hako.rentACar.dataAccess.abstracts.ModelRepository;
import hako.rentACar.dto.model.responses.GetAllModelsResponse;
import hako.rentACar.entities.concretes.Model;

@Service
public class ModelManager implements ModelService {
  private ModelRepository modelRepository;
  private ModelMapperService modelMapper;

  public ModelManager(ModelRepository modelRepository, ModelMapperService modelMapperService) {
    this.modelRepository = modelRepository;
    this.modelMapper = modelMapperService;
  }

  @Override
  public List<GetAllModelsResponse> getAll() {
    List<Model> models = this.modelRepository.findAll();
    List<GetAllModelsResponse> modelsResponse = models.stream().map(
      model -> this.modelMapper.forResponse().map(model, GetAllModelsResponse.class)
    ).collect(Collectors.toList());

    return modelsResponse;
  }
  
}
