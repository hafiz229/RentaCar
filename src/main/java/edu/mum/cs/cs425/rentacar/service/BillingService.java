package edu.mum.cs.cs425.rentacar.service;

import edu.mum.cs.cs425.rentacar.entity.Billing;
import edu.mum.cs.cs425.rentacar.entity.Rental;
import edu.mum.cs.cs425.rentacar.repository.BillingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final BillingRepository billingRepository;

    public Billing generateBill(Rental rental) {

        long totalDays = ChronoUnit.DAYS.between(
                rental.getRentalDate(),
                rental.getReturnDate());

        // Minimum one-day charge
        if (totalDays <= 0) {
            totalDays = 1;
        }

        double totalAmount =
                totalDays * rental.getVehicle().getDailyRate();

        Billing billing = Billing.builder()
                .rental(rental)
                .totalAmount(totalAmount)
                .paid(false)
                .build();

        return billingRepository.save(billing);
    }

    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }

    public Billing getBill(Long id) {
        return billingRepository.findById(id).orElse(null);
    }

    public void markAsPaid(Long id) {

        Billing billing = getBill(id);

        if (billing != null) {

            billing.setPaid(true);

            billingRepository.save(billing);

        }
    }

    public long getBillCount() {
        return billingRepository.count();
    }

}