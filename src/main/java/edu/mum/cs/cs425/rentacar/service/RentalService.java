package edu.mum.cs.cs425.rentacar.service;

import edu.mum.cs.cs425.rentacar.entity.Customer;
import edu.mum.cs.cs425.rentacar.entity.Rental;
import edu.mum.cs.cs425.rentacar.entity.Vehicle;
import edu.mum.cs.cs425.rentacar.repository.CustomerRepository;
import edu.mum.cs.cs425.rentacar.repository.RentalRepository;
import edu.mum.cs.cs425.rentacar.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final BillingService billingService;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public Rental createRental(Long customerId,
                               Long vehicleId,
                               String rentalDate,
                               String returnDate) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (!Boolean.TRUE.equals(vehicle.getAvailable())) {
            throw new RuntimeException("Vehicle is already rented.");
        }

        Rental rental = new Rental();

        rental.setCustomer(customer);
        rental.setVehicle(vehicle);
        rental.setRentalDate(LocalDate.parse(rentalDate));
        rental.setReturnDate(LocalDate.parse(returnDate));
        rental.setReturned(false);

        Rental savedRental = rentalRepository.save(rental);

        vehicle.setAvailable(false);
        vehicleRepository.save(vehicle);

        return savedRental;
    }

    public void returnVehicle(Long rentalId) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        if (Boolean.TRUE.equals(rental.getReturned())) {
            return;
        }

        rental.setReturned(true);

        rentalRepository.save(rental);

        Vehicle vehicle = rental.getVehicle();

        vehicle.setAvailable(true);

        vehicleRepository.save(vehicle);

        billingService.generateBill(rental);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    public long getRentalCount() {
        return rentalRepository.count();
    }

}