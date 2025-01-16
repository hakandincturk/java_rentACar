package hako.rentACar.webApi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hako.rentACar.business.abstracts.CarService;
import hako.rentACar.core.utilities.results.DataResult;
import hako.rentACar.core.utilities.results.Result;
import hako.rentACar.core.utilities.results.SuccessDataResult;
import hako.rentACar.core.utilities.results.SuccessResult;
import hako.rentACar.dto.car.requests.CreateCarRequest;
import hako.rentACar.dto.car.responses.GetAllCarsResponse;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/cars")
public class CarController {

  private CarService carService;

  private CarController(CarService carService) {
    this.carService = carService;
  }

  @GetMapping()
  public DataResult<List<GetAllCarsResponse>> getAllCars() {
    List<GetAllCarsResponse> data = this.carService.getAllCars();
    return new SuccessDataResult<List<GetAllCarsResponse>>("Cars get okey", data);
  }

  @PostMapping()
  @ResponseStatus(code = HttpStatus.CREATED)
  public Result createNewCar(@RequestBody @Valid CreateCarRequest createCarRequest) {
    this.carService.add(createCarRequest);
    return new SuccessResult("Car added");    
  }
  
}
