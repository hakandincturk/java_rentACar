package hako.rentACar.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hako.rentACar.business.abstracts.ModelService;
import hako.rentACar.dto.model.responses.GetAllModelsResponse;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/models")
public class ModelController {

  private ModelService modelService;
  private ModelController(ModelService modelService) {
    this.modelService = modelService;
  }

  @GetMapping()
  public List<GetAllModelsResponse> getAll() {
    return this.modelService.getAll();
  }
  
}
