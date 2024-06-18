package hako.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hako.rentACar.business.abstracts.BrandService;
import hako.rentACar.core.utilities.mappers.ModelMapperService;
import hako.rentACar.dataAccess.abstracts.BrandRepository;
import hako.rentACar.dto.brand.requests.CreateBrandRequest;
import hako.rentACar.dto.brand.requests.UpdateBrandRequest;
import hako.rentACar.dto.brand.responses.GetAllBrandsResponse;
import hako.rentACar.dto.brand.responses.GetByIdBrandResponse;
import hako.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {
  private BrandRepository brandRepository;
  private ModelMapperService modelMapperService;

  public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
    this.brandRepository = brandRepository;
    this.modelMapperService = modelMapperService;
  }

  @Override
  public List<GetAllBrandsResponse> getAll() {

    List<Brand> brands = brandRepository.findAll();
    List<GetAllBrandsResponse> brandsResponse = brands.stream().map(
      brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)
    ).collect(Collectors.toList());

    return brandsResponse;
  }

  @Override
  public void add(CreateBrandRequest request) {
    Brand brand = this.modelMapperService.forRequest().map(request, Brand.class);
    brandRepository.save(brand);
  }

  @Override
  public GetByIdBrandResponse getByIdBrand(int id) {
    Brand brand = brandRepository.findById(id).orElse(null);
    GetByIdBrandResponse brandResponse = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
    return brandResponse;
  }

  @Override
  public void update(UpdateBrandRequest request) {
    Brand brand = this.modelMapperService.forRequest().map(request, Brand.class);
    this.brandRepository.save(brand);
    return;
  }

  @Override
  public void delete(int id) {
    this.brandRepository.deleteById(id);
    return;
  }
  
}
