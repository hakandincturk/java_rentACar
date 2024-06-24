package hako.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hako.rentACar.business.abstracts.ModelService;
import hako.rentACar.business.rules.ModelBusinessRules;
import hako.rentACar.core.utilities.mappers.ModelMapperService;
import hako.rentACar.dataAccess.abstracts.ModelRepository;
import hako.rentACar.dto.brand.requests.UpdateBrandRequest;
import hako.rentACar.dto.model.requests.CreateModelRequest;
import hako.rentACar.dto.model.responses.GetAllModelsResponse;
import hako.rentACar.dto.model.responses.GetModelResponse;
import hako.rentACar.entities.concretes.Model;

@Service
public class ModelManager implements ModelService {
  private ModelRepository modelRepository;
  private ModelMapperService modelMapper;
  private ModelBusinessRules modelBusinessRules;

  public ModelManager(ModelRepository modelRepository, ModelMapperService modelMapperService, ModelBusinessRules modelBusinessRules) {
    this.modelRepository = modelRepository;
    this.modelMapper = modelMapperService;
    this.modelBusinessRules = modelBusinessRules;
  }

  @Override
  public List<GetAllModelsResponse> getAll() {
    List<Model> models = this.modelRepository.findAll();
    List<GetAllModelsResponse> modelsResponse = models.stream().map(
      model -> this.modelMapper.forResponse().map(model, GetAllModelsResponse.class)
    ).collect(Collectors.toList());

    return modelsResponse;
  }

  @Override
  public void add(CreateModelRequest request) {
    Model model = this.modelMapper.forRequest().map(request, Model.class);
    this.modelRepository.save(model);
  }

  @Override
  public GetModelResponse getOne(int modelId) {
    this.modelBusinessRules.checkIfModelExists(modelId);
    Model models = this.modelRepository.findById(modelId).orElse(null);
    GetModelResponse modelResponse = this.modelMapper.forResponse().map(models, GetModelResponse.class);
    
    return modelResponse;
  }

  @Override
  public void update(UpdateBrandRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
  
}
