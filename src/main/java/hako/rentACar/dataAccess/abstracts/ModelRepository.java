package hako.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hako.rentACar.entities.concretes.Model;


public interface ModelRepository extends JpaRepository<Model, Integer>{
  List<Model> findByIsRemovedFalse();
  boolean existsByName(String name);
}
