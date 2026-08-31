package edu.mum.cs.cs425.rentacar.controller;

import edu.mum.cs.cs425.rentacar.entity.Rental;
import edu.mum.cs.cs425.rentacar.service.CustomerService;
import edu.mum.cs.cs425.rentacar.service.RentalService;
import edu.mum.cs.cs425.rentacar.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final CustomerService customerService;
    private final VehicleService vehicleService;

    @GetMapping
    public String listRentals(Model model) {

        model.addAttribute("rentals",
                rentalService.getAllRentals());

        return "rental/list";
    }

    @GetMapping("/new")
    public String showRentalForm(Model model) {

        model.addAttribute("rental", new Rental());

        model.addAttribute("customers",
                customerService.getAllCustomers());

        model.addAttribute("vehicles",
                vehicleService.getAvailableVehicles());

        return "rental/form";
    }

    @PostMapping("/save")
    public String saveRental(
            @RequestParam("customerId") Long customerId,
            @RequestParam("vehicleId") Long vehicleId,
            @RequestParam("rentalDate") String rentalDate,
            @RequestParam("returnDate") String returnDate) {

        rentalService.createRental(
                customerId,
                vehicleId,
                rentalDate,
                returnDate);

        return "redirect:/rentals";
    }

    @GetMapping("/return/{id}")
    public String returnVehicle(@PathVariable Long id) {

        rentalService.returnVehicle(id);

        return "redirect:/billings";
    }

    @GetMapping("/delete/{id}")
    public String deleteRental(@PathVariable Long id) {

        rentalService.deleteRental(id);

        return "redirect:/rentals";
    }

}