package hako.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hako.rentACar.business.abstracts.CarService;
import hako.rentACar.business.rules.ModelBusinessRules;
import hako.rentACar.core.utilities.mappers.ModelMapperService;
import hako.rentACar.dataAccess.abstracts.CarRepository;
import hako.rentACar.dataAccess.abstracts.ModelRepository;
import hako.rentACar.dto.car.requests.CreateCarRequest;
import hako.rentACar.dto.car.responses.GetAllCarsResponse;
import hako.rentACar.entities.concretes.Car;
import hako.rentACar.entities.concretes.Model;

@Service
public class CarManager implements CarService {

  private CarRepository carRepository;
  private ModelBusinessRules modelBusinessRules;
  private ModelMapperService modelMapper;
  private ModelRepository modelRepository;

  public CarManager(CarRepository carRepository, ModelMapperService modelMapper, ModelBusinessRules modelBusinessRules, ModelRepository modelRepository) {
    this.carRepository = carRepository;
    this.modelMapper = modelMapper;
    this.modelBusinessRules = modelBusinessRules;
    this.modelRepository = modelRepository;
  }


  @Override
  public List<GetAllCarsResponse> getAllCars() {
    List<Car> cars = carRepository.findAll();
    List<GetAllCarsResponse> modelResponse = cars.stream().map(
      car -> this.modelMapper.forResponse().map(car, GetAllCarsResponse.class)
    ).collect(Collectors.toList());

    return modelResponse;
  }


  @Override
  public void add(CreateCarRequest request) {
    this.modelBusinessRules.checkIfModelExists(request.getModelId());
    Model model = this.modelRepository.findById(request.getModelId()).orElse(null);

    Car car = new Car();
    car.setModel(model);
    car.setModelYear(request.getModelYear());
    car.setDailyPrice(request.getDailyPrice());
    car.setPlate(request.getPlate());
    car.setState(0); // TODO ENUM
    car.setKm(request.getKm());
    this.carRepository.save(car);
  }

}
