package edu.mum.cs.cs425.rentacar.repository;

import edu.mum.cs.cs425.rentacar.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
