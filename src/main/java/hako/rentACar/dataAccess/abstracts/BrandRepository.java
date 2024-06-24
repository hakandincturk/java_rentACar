package hako.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hako.rentACar.entities.concretes.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
  boolean existsByName(String name);
  
  @Query("SELECT b FROM Brand b JOIN FETCH b.models m WHERE b.id = :id")
  Brand getByIdWithModels(@Param("id")int id);
}
