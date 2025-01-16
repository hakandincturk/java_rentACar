package hako.rentACar.webApi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hako.rentACar.business.abstracts.LocationService;
import hako.rentACar.core.utilities.results.DataResult;
import hako.rentACar.core.utilities.results.Result;
import hako.rentACar.dto.location.requests.CreateLocationRequest;
import hako.rentACar.dto.location.requests.UpdateLocationRequest;
import hako.rentACar.dto.location.responses.GetAllLocationsResponses;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/locations")
public class LocationController {

  private LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping()
  public DataResult<List<GetAllLocationsResponses>> findAll() {
    List<GetAllLocationsResponses> data = this.locationService.getAll();
    return new DataResult<List<GetAllLocationsResponses>>(true, "Locations get okey", data);
  }

  @PostMapping()
  public Result createNewLocation(@RequestBody @Valid CreateLocationRequest createLocationRequest) {
    this.locationService.add(createLocationRequest);
    return new Result(true, "Location added");
  }

  @PutMapping("/{id}")
  public Result update(@PathVariable int id, @RequestBody @Valid UpdateLocationRequest updateLocationRequest) {
    this.locationService.update(id, updateLocationRequest);
    return new Result(true, "Location updated");
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable int id) {
    this.locationService.delete(id);
    return new Result(true, "Location deleted");
  }
  
  
  
}
