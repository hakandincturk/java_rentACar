package hako.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hako.rentACar.business.abstracts.LocationService;
import hako.rentACar.business.rules.LocationBusinessRules;
import hako.rentACar.core.utilities.mappers.ModelMapperService;
import hako.rentACar.dataAccess.abstracts.LocationRepository;
import hako.rentACar.dto.location.requests.CreateLocationRequest;
import hako.rentACar.dto.location.requests.UpdateLocationRequest;
import hako.rentACar.dto.location.responses.GetAllLocationsResponses;
import hako.rentACar.entities.concretes.Location;

@Service
public class LocationManager implements LocationService {

  private LocationRepository locationRepository;
  private LocationBusinessRules locationBusinessRules;
  private ModelMapperService modelMapper;

  public LocationManager(LocationRepository locationRepository, ModelMapperService modelMapper, LocationBusinessRules locationBusinessRules) {
    this.locationRepository = locationRepository;
    this.modelMapper = modelMapper;
    this.locationBusinessRules = locationBusinessRules;
  }

  @Override
  public List<GetAllLocationsResponses> getAll() {
    List<Location> locations = locationRepository.findAll();
    List<GetAllLocationsResponses> locationResponse = locations.stream().map(
      location -> this.modelMapper.forResponse().map(location, GetAllLocationsResponses.class)
    ).collect(Collectors.toList());

    return locationResponse;
  }

  @Override
  public void add(CreateLocationRequest createLocationRequest) {
    this.locationBusinessRules.checkIfLocationNameExists(createLocationRequest.getName());

    Location location = new Location();
    location.setName(createLocationRequest.getName());
    location.setAddress(createLocationRequest.getAdress());
    
    locationRepository.save(location);
  }

  @Override
  public void delete(int locationId) {
    this.locationBusinessRules.checkIfLocationExists(locationId);
    locationRepository.deleteById(locationId);
  }

  @Override
  public void update(int locationId, UpdateLocationRequest updateLocationRequest) {
    this.locationBusinessRules.checkIfLocationExists(locationId);

    Location location = locationRepository.findById(locationId).orElse(null);
    location.setName(updateLocationRequest.getName());
    location.setAddress(updateLocationRequest.getAdress());

    locationRepository.save(location);
  }
  
}
