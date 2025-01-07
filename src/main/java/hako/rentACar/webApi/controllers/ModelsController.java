package hako.rentACar.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hako.rentACar.business.abstracts.ModelService;
import hako.rentACar.core.utilities.results.DataResult;
import hako.rentACar.core.utilities.results.Result;
import hako.rentACar.core.utilities.results.SuccessDataResult;
import hako.rentACar.core.utilities.results.SuccessResult;
import hako.rentACar.dto.model.requests.CreateModelRequest;
import hako.rentACar.dto.model.requests.UpdateModelRequest;
import hako.rentACar.dto.model.responses.GetAllModelsResponse;
import hako.rentACar.dto.model.responses.GetModelResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/models")
public class ModelsController {

  private ModelService modelService;
  private ModelsController(ModelService modelService) {
    this.modelService = modelService;
  }

  @GetMapping("/{id}")
  public DataResult<GetModelResponse> getOne(@RequestParam int modelId) {
    GetModelResponse data = this.modelService.getOne(modelId);
    return new SuccessDataResult<GetModelResponse>("Models get okey", data);
  }

  @GetMapping()
  public DataResult<List<GetAllModelsResponse>> getAll() {
    List<GetAllModelsResponse> data = this.modelService.getAll();
    return new SuccessDataResult<List<GetAllModelsResponse>>("Models get okey", data);
  }

  @PostMapping()
  @ResponseStatus(code = HttpStatus.CREATED)
  public Result add(@RequestBody @Valid CreateModelRequest createModelRequest) {
    this.modelService.add(createModelRequest);
    return new SuccessResult("Model added");
  }

  @PutMapping("/{modelId}")
  @ResponseStatus(code = HttpStatus.OK)
  public Result update(@RequestParam int modelId, @RequestBody @Valid UpdateModelRequest updateModelRequest) {
    this.modelService.update(modelId, updateModelRequest);
    return new SuccessResult("Model updated");
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Result delete(@RequestParam int id) {
    this.modelService.delete(id);
    return new SuccessResult("Model deleted");
  }
  
}
