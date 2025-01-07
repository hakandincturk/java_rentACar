package hako.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hako.rentACar.business.abstracts.ModelService;
import hako.rentACar.business.rules.BrandBusinessRules;
import hako.rentACar.business.rules.ModelBusinessRules;
import hako.rentACar.core.utilities.mappers.ModelMapperService;
import hako.rentACar.dataAccess.abstracts.ModelRepository;
import hako.rentACar.dataAccess.abstracts.BrandRepository;
import hako.rentACar.dto.model.requests.CreateModelRequest;
import hako.rentACar.dto.model.requests.UpdateModelRequest;
import hako.rentACar.dto.model.responses.GetAllModelsResponse;
import hako.rentACar.dto.model.responses.GetModelResponse;
import hako.rentACar.entities.concretes.Brand;
import hako.rentACar.entities.concretes.Model;
import jakarta.transaction.Transactional;

@Service
public class ModelManager implements ModelService {
  private ModelRepository modelRepository;
  private ModelMapperService modelMapper;
  private ModelBusinessRules modelBusinessRules;

  private BrandRepository brandRepository;
  private BrandBusinessRules brandBusinessRules;


  public ModelManager(ModelRepository modelRepository, ModelMapperService modelMapperService, ModelBusinessRules modelBusinessRules, BrandRepository brandRepository, BrandBusinessRules brandBusinessRules) {
    this.modelRepository = modelRepository;
    this.modelMapper = modelMapperService;
    this.modelBusinessRules = modelBusinessRules;
    this.brandRepository = brandRepository;
    this.brandBusinessRules = brandBusinessRules;
  }

  @Override
  @Transactional
  public List<GetAllModelsResponse> getAll() {
    List<Model> models = this.modelRepository.findByIsRemovedFalse();
    List<GetAllModelsResponse> modelsResponse = models.stream().map(
      model -> this.modelMapper.forResponse().map(model, GetAllModelsResponse.class)
    ).collect(Collectors.toList());

    return modelsResponse;
  }

  @Override
  @Transactional
  public void add(CreateModelRequest createModelRequest) {
    // bu daha sonra denenecek
    // System.out.println("ModelManager request-->" + createModelRequest);
    // Model model = this.modelMapper.forRequest().map(createModelRequest, Model.class);
    // System.out.println("ModelManager model -->" + model);
    Brand brand = this.brandRepository.findById(createModelRequest.getBrandId()).orElse(null);
    this.brandBusinessRules.checkIfBrandExists(createModelRequest.getBrandId());
    
    Model model = new Model();
    model.setName(createModelRequest.getName());
    model.setBrand(brand);

    this.modelRepository.save(model);
  }

  @Override
  @Transactional
  public GetModelResponse getOne(int modelId) {
    this.modelBusinessRules.checkIfModelExists(modelId);
    Model models = this.modelRepository.findById(modelId).orElse(null);
    GetModelResponse modelResponse = this.modelMapper.forResponse().map(models, GetModelResponse.class);
    
    return modelResponse;
  }

  @Override
  @Transactional
  public void update(int modelId, UpdateModelRequest request) {
    this.modelBusinessRules.checkIfModelExists(modelId);
    this.brandBusinessRules.checkIfBrandExists(request.getBrandId());
    this.modelBusinessRules.checkIfModelNameExists(request.getName());

    Brand brand = this.brandRepository.findById(request.getBrandId()).orElse(null);
    Model model = this.modelRepository.findById(request.getBrandId()).orElse(null);
    model.setName(request.getName());
    model.setBrand(brand);
    this.modelRepository.save(model);
  }

  @Override
  @Transactional
  public void delete(int id) {
    
    this.modelBusinessRules.checkIfModelExists(id);
    Model model = this.modelRepository.findById(id).orElse(null);
    model.setRemoved(true);
    this.modelRepository.save(model);
  }
  
}
