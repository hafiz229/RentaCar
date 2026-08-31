package edu.mum.cs.cs425.rentacar.service;

import edu.mum.cs.cs425.rentacar.entity.Vehicle;
import edu.mum.cs.cs425.rentacar.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void shouldReturnAllVehicles() {

        when(vehicleRepository.findAll())
                .thenReturn(List.of(
                        Vehicle.builder().id(1L).brand("Toyota").build(),
                        Vehicle.builder().id(2L).brand("Honda").build()
                ));

        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        assertEquals(2, vehicles.size());
    }
}