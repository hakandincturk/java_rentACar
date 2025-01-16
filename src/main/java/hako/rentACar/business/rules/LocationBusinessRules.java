package hako.rentACar.business.rules;

import org.springframework.stereotype.Service;

import hako.rentACar.core.utilities.results.exceptions.BusinessException;
import hako.rentACar.dataAccess.abstracts.LocationRepository;

@Service
public class LocationBusinessRules {

  private LocationRepository locationRepository;

  public LocationBusinessRules(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public void checkIfLocationNameExists(String name) {
    if (this.locationRepository.existsByName(name)) {
      throw new BusinessException("Location name already exists.");
    }
  }

  public void checkIfLocationExists(int locationId) {
    if (this.locationRepository.findById(locationId).isEmpty()) {
      throw new BusinessException("Location not found");
    }
  }
  
}
