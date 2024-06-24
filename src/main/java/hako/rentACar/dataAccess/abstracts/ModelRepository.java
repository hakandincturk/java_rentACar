package hako.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hako.rentACar.entities.concretes.Model;


public interface ModelRepository extends JpaRepository<Model, Integer>{
  boolean existsByName(String name);
}
