package edu.mum.cs.cs425.rentacar.service;

import edu.mum.cs.cs425.rentacar.entity.Vehicle;
import edu.mum.cs.cs425.rentacar.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByAvailableTrue();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public void markAsRented(Long vehicleId) {

        Vehicle vehicle = getVehicleById(vehicleId);

        if (vehicle != null) {
            vehicle.setAvailable(false);
            vehicleRepository.save(vehicle);
        }
    }

    public void markAsAvailable(Long vehicleId) {

        Vehicle vehicle = getVehicleById(vehicleId);

        if (vehicle != null) {
            vehicle.setAvailable(true);
            vehicleRepository.save(vehicle);
        }
    }

    public long getVehicleCount() {
        return vehicleRepository.count();
    }

    public long getAvailableVehicleCount() {
        return vehicleRepository.findByAvailableTrue().size();
    }
}