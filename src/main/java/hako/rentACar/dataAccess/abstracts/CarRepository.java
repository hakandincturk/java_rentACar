package hako.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hako.rentACar.entities.concretes.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{  
}
