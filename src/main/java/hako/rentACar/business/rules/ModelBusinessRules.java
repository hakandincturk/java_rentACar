package hako.rentACar.business.rules;

import org.springframework.stereotype.Service;

import hako.rentACar.core.utilities.results.exceptions.BusinessException;
import hako.rentACar.dataAccess.abstracts.ModelRepository;

@Service
public class ModelBusinessRules {

  private ModelRepository modelRepository;
  public ModelBusinessRules(ModelRepository modelRepository) {
    this.modelRepository = modelRepository;
  }

  public void checkIfModelNameExists(String name) {
    if (this.modelRepository.existsByName(name)) {
      throw new BusinessException("Model name already exists.");
    }
  }

  public void checkIfModelExists(int brandId) {
    if (this.modelRepository.findById(brandId).isEmpty()) {
      throw new BusinessException("Model not found");
    }
  }
}
