package hako.rentACar.business.abstracts;

import java.util.List;

import hako.rentACar.dto.brand.requests.CreateBrandRequest;
import hako.rentACar.dto.brand.requests.UpdateBrandRequest;
import hako.rentACar.dto.brand.responses.GetAllBrandsResponse;
import hako.rentACar.dto.brand.responses.GetByIdBrandResponse;

public interface BrandService {
  List<GetAllBrandsResponse> getAll();
  GetByIdBrandResponse getByIdBrand(int id);
  void add(CreateBrandRequest request);
  void update(UpdateBrandRequest request);
  void delete(int id);
}
