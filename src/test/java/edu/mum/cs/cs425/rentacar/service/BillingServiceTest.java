package edu.mum.cs.cs425.rentacar.service;

import edu.mum.cs.cs425.rentacar.entity.Billing;
import edu.mum.cs.cs425.rentacar.entity.Rental;
import edu.mum.cs.cs425.rentacar.entity.Vehicle;
import edu.mum.cs.cs425.rentacar.repository.BillingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillingServiceTest {

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingService billingService;

    @Test
    void shouldGenerateBill() {

        Vehicle vehicle = Vehicle.builder()
                .dailyRate(100.00)
                .build();

        Rental rental = Rental.builder()
                .vehicle(vehicle)
                .rentalDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(3))
                .build();

        Billing billing = Billing.builder()
                .totalAmount(300.00)
                .build();

        when(billingRepository.save(any(Billing.class)))
                .thenReturn(billing);

        Billing result = billingService.generateBill(rental);

        assertEquals(300, result.getTotalAmount());
    }
}