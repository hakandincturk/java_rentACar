package hako.rentACar.business.rules;

import org.springframework.stereotype.Service;

import hako.rentACar.core.utilities.results.exceptions.BusinessException;
import hako.rentACar.dataAccess.abstracts.BrandRepository;

@Service
public class BrandBusinessRules {

  private BrandRepository brandRepository;
  
  public BrandBusinessRules(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public void checkIfBrandNameExists(String name) {
    if (this.brandRepository.existsByName(name)) {
      throw new BusinessException("Brand name already exists.");
    }
  }
}
