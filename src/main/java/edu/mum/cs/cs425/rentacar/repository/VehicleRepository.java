package edu.mum.cs.cs425.rentacar.repository;

import edu.mum.cs.cs425.rentacar.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByAvailableTrue();

}
