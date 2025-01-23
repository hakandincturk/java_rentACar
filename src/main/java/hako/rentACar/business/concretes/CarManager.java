package hako.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hako.rentACar.business.abstracts.CarService;
import hako.rentACar.business.rules.CarBusinessRules;
import hako.rentACar.business.rules.LocationBusinessRules;
import hako.rentACar.business.rules.ModelBusinessRules;
import hako.rentACar.core.utilities.mappers.ModelMapperService;
import hako.rentACar.dataAccess.abstracts.CarRepository;
import hako.rentACar.dataAccess.abstracts.LocationRepository;
import hako.rentACar.dataAccess.abstracts.ModelRepository;
import hako.rentACar.dto.car.requests.CreateCarRequest;
import hako.rentACar.dto.car.requests.UpdateCarRequest;
import hako.rentACar.dto.car.responses.GetAllCarsResponse;
import hako.rentACar.entities.concretes.Car;
import hako.rentACar.entities.concretes.Location;
import hako.rentACar.entities.concretes.Model;

@Service
public class CarManager implements CarService {

  private CarRepository carRepository;
  private CarBusinessRules carBusinessRules;

  private ModelBusinessRules modelBusinessRules;
  private ModelMapperService modelMapper;

  private ModelRepository modelRepository;

  private LocationBusinessRules locationBusinessRules;
  private LocationRepository locationRepository;

  public CarManager(
    CarRepository carRepository,
    CarBusinessRules carBusinessRules,
    ModelMapperService modelMapper,
    ModelBusinessRules modelBusinessRules,
    ModelRepository modelRepository,
    LocationBusinessRules locationBusinessRules,
    LocationRepository locationRepository
    ) {
    this.carRepository = carRepository;
    this.carBusinessRules = carBusinessRules;
    this.modelMapper = modelMapper;
    this.modelBusinessRules = modelBusinessRules;
    this.modelRepository = modelRepository;
    this.locationBusinessRules = locationBusinessRules;
    this.locationRepository = locationRepository;
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

    this.locationBusinessRules.checkIfLocationExists(request.getLocationId());
    Location location = this.locationRepository.findById(request.getLocationId()).orElse(null);

    Car car = new Car();
    car.setModel(model);
    car.setModelYear(request.getModelYear());
    car.setDailyPrice(request.getDailyPrice());
    car.setPlate(request.getPlate());
    car.setState(0); // TODO ENUM
    car.setKm(request.getKm());
    car.setLocation(location);
    this.carRepository.save(car);
  }


  @Override
  public void update(int carId, UpdateCarRequest request) {

    this.carBusinessRules.checkIfCarExists(carId);
    Car car = this.carRepository.findById(carId).orElse(null);

    this.modelBusinessRules.checkIfModelExists(request.getModelId());
    Model model = this.modelRepository.findById(request.getModelId()).orElse(null);

    this.locationBusinessRules.checkIfLocationExists(request.getLocationId());
    Location location = this.locationRepository.findById(request.getLocationId()).orElse(null);

    car.setModel(model);
    car.setModelYear(request.getModelYear());
    car.setDailyPrice(request.getDailyPrice());
    car.setPlate(request.getPlate());
    car.setState(0); // TODO ENUM
    car.setKm(request.getKm());
    car.setLocation(location);

    this.carRepository.save(car);
  }

}
