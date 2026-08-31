package edu.mum.cs.cs425.rentacar.controller;

import edu.mum.cs.cs425.rentacar.entity.Vehicle;
import edu.mum.cs.cs425.rentacar.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public String listVehicles(Model model) {

        model.addAttribute("vehicles",
                vehicleService.getAllVehicles());

        return "vehicle/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {

        model.addAttribute("vehicle",
                new Vehicle());

        return "vehicle/form";
    }

    @PostMapping("/save")
    public String saveVehicle(
            @Valid @ModelAttribute("vehicle") Vehicle vehicle,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vehicle/form";
        }

        vehicleService.saveVehicle(vehicle);

        return "redirect:/vehicles";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Vehicle vehicle =
                vehicleService.getVehicleById(id);

        if (vehicle == null) {
            return "redirect:/vehicles";
        }

        model.addAttribute("vehicle",
                vehicle);

        return "vehicle/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(
            @PathVariable Long id) {

        vehicleService.deleteVehicle(id);

        return "redirect:/vehicles";
    }
}