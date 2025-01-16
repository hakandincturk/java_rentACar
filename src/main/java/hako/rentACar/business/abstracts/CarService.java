package hako.rentACar.business.abstracts;

import java.util.List;

import hako.rentACar.dto.car.requests.CreateCarRequest;
import hako.rentACar.dto.car.responses.GetAllCarsResponse;

public interface CarService {
  List<GetAllCarsResponse> getAllCars();
  void add(CreateCarRequest request);
}
