package hako.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hako.rentACar.entities.concretes.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
  
  boolean existsByName(String locationName);

}
