package hako.rentACar.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hako.rentACar.business.abstracts.ModelService;
import hako.rentACar.dto.model.requests.CreateModelRequest;
import hako.rentACar.dto.model.responses.GetAllModelsResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/models")
public class ModelsController {

  private ModelService modelService;
  private ModelsController(ModelService modelService) {
    this.modelService = modelService;
  }

  @GetMapping()
  public List<GetAllModelsResponse> getAll() {
    return this.modelService.getAll();
  }

  @PostMapping()
  @ResponseStatus(code = HttpStatus.CREATED)
  public void add(@RequestBody @Valid CreateModelRequest createModelRequest) {
    this.modelService.add(createModelRequest);
  }
  
}