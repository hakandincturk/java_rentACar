package hako.rentACar.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hako.rentACar.business.abstracts.BrandService;
import hako.rentACar.dto.brand.requests.CreateBrandRequest;
import hako.rentACar.dto.brand.requests.UpdateBrandRequest;
import hako.rentACar.dto.brand.responses.GetAllBrandsResponse;
import hako.rentACar.dto.brand.responses.GetByIdBrandResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController // annotation for rest api
@RequestMapping("/api/brands") // base url
public class BrandsController {
  private BrandService brandService;

  public BrandsController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping() // get request
  public List<GetAllBrandsResponse> getAll() {
    return this.brandService.getAll();
  }

  @GetMapping("/{id}")
  public GetByIdBrandResponse getMethodName(@RequestParam int id) {
      return this.brandService.getByIdBrand(id);
  }

  @PostMapping()
  @ResponseStatus(code = HttpStatus.CREATED)
  public void add(@RequestBody CreateBrandRequest request) {
    this.brandService.add(request);
  }

  @PutMapping()
  public Boolean update(@RequestBody UpdateBrandRequest request) {
    this.brandService.update(request);
    return true;
  }

  @DeleteMapping("/{id}")
  public Boolean delete(@PathVariable int id) {
    this.brandService.delete(id);
    return true;
  }
}
