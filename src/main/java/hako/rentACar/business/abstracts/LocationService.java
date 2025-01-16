package hako.rentACar.business.abstracts;

import java.util.List;

import hako.rentACar.dto.location.requests.CreateLocationRequest;
import hako.rentACar.dto.location.requests.UpdateLocationRequest;
import hako.rentACar.dto.location.responses.GetAllLocationsResponses;

public interface LocationService {
  List<GetAllLocationsResponses> getAll();
  void add(CreateLocationRequest createLocationRequest);
  void delete(int locationId);
  void update(int locationId, UpdateLocationRequest updateLocationRequest);
}
