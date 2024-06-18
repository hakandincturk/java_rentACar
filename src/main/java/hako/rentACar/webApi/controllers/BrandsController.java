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
import hako.rentACar.core.utilities.results.DataResult;
import hako.rentACar.core.utilities.results.Result;
import hako.rentACar.core.utilities.results.SuccessDataResult;
import hako.rentACar.core.utilities.results.SuccessResult;
import hako.rentACar.dto.brand.requests.CreateBrandRequest;
import hako.rentACar.dto.brand.requests.UpdateBrandRequest;
import hako.rentACar.dto.brand.responses.GetAllBrandsResponse;
import hako.rentACar.dto.brand.responses.GetByIdBrandResponse;
import jakarta.validation.Valid;

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
  public DataResult<List<GetAllBrandsResponse>> getAll() {
    List<GetAllBrandsResponse> data = this.brandService.getAll();
    SuccessDataResult<List<GetAllBrandsResponse>> result = new SuccessDataResult<>("Brands get okey", data);
    return result;
  }

  @GetMapping("/{id}")
  public DataResult<GetByIdBrandResponse> getMethodName(@RequestParam int id) {
    GetByIdBrandResponse data = this.brandService.getByIdBrand(id);
    SuccessDataResult<GetByIdBrandResponse> result = new SuccessDataResult<>("Brand get okey", data);
    return result;
  }

  @PostMapping()
  @ResponseStatus(code = HttpStatus.CREATED)
  public Result add(@RequestBody @Valid CreateBrandRequest request) {
    this.brandService.add(request);
    return new SuccessResult("Brand added");
  }

  @PutMapping()
  public Result update(@RequestBody UpdateBrandRequest request) {
    this.brandService.update(request);
    return new SuccessResult("Brand updated");
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable int id) {
    this.brandService.delete(id);
    return new SuccessResult("Brand deleted");
  }
}
