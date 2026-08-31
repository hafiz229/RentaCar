package edu.mum.cs.cs425.rentacar.config;

import edu.mum.cs.cs425.rentacar.entity.Customer;
import edu.mum.cs.cs425.rentacar.entity.Vehicle;
import edu.mum.cs.cs425.rentacar.entity.VehicleCategory;
import edu.mum.cs.cs425.rentacar.repository.CustomerRepository;
import edu.mum.cs.cs425.rentacar.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {

        if (vehicleRepository.count() == 0) {

            vehicleRepository.save(
                    Vehicle.builder()
                            .brand("Toyota")
                            .model("Camry")
                            .category(VehicleCategory.SEDAN)
                            .dailyRate(75.0)
                            .available(true)
                            .build());

            vehicleRepository.save(
                    Vehicle.builder()
                            .brand("Honda")
                            .model("CR-V")
                            .category(VehicleCategory.SUV)
                            .dailyRate(90.0)
                            .available(true)
                            .build());

            vehicleRepository.save(
                    Vehicle.builder()
                            .brand("Ford")
                            .model("Transit")
                            .category(VehicleCategory.VAN)
                            .dailyRate(120.0)
                            .available(true)
                            .build());

            vehicleRepository.save(
                    Vehicle.builder()
                            .brand("Chevrolet")
                            .model("Silverado")
                            .category(VehicleCategory.TRUCK)
                            .dailyRate(150.0)
                            .available(true)
                            .build());

        }

        if (customerRepository.count() == 0) {

            customerRepository.save(
                    Customer.builder()
                            .firstName("John")
                            .lastName("Smith")
                            .email("john@email.com")
                            .phoneNumber("515-111-1111")
                            .active(true)
                            .build());

            customerRepository.save(
                    Customer.builder()
                            .firstName("Anna")
                            .lastName("Johnson")
                            .email("anna@email.com")
                            .phoneNumber("515-222-2222")
                            .active(true)
                            .build());

            customerRepository.save(
                    Customer.builder()
                            .firstName("David")
                            .lastName("Wilson")
                            .email("david@email.com")
                            .phoneNumber("515-333-3333")
                            .active(true)
                            .build());

        }

        System.out.println();
        System.out.println("=======================================");
        System.out.println("   SAMPLE DATA LOADED SUCCESSFULLY");
        System.out.println("=======================================");
        System.out.println("Vehicles : " + vehicleRepository.count());
        System.out.println("Customers: " + customerRepository.count());
        System.out.println();

    }
}
