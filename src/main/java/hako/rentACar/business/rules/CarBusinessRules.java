package hako.rentACar.business.rules;

import hako.rentACar.core.utilities.results.exceptions.BusinessException;
import hako.rentACar.dataAccess.abstracts.CarRepository;

public class CarBusinessRules {

  private CarRepository carRepository;

  public CarBusinessRules(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public void checkIfCarExists(int carId) {
    if (this.carRepository.findById(carId).isEmpty()) {
      throw new BusinessException("Car not found");
    }
  }
  
}
