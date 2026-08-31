package edu.mum.cs.cs425.rentacar.repository;

import edu.mum.cs.cs425.rentacar.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {

}