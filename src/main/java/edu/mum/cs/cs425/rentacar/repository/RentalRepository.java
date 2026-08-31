package edu.mum.cs.cs425.rentacar.repository;

import edu.mum.cs.cs425.rentacar.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}