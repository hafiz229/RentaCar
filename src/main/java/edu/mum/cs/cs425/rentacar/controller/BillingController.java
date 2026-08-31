package edu.mum.cs.cs425.rentacar.controller;

import edu.mum.cs.cs425.rentacar.entity.Billing;
import edu.mum.cs.cs425.rentacar.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/billings")
public class BillingController {

    private final BillingService billingService;

    @GetMapping
    public String listBills(Model model) {

        model.addAttribute(
                "billings",
                billingService.getAllBills());

        return "billing/list";
    }

    @GetMapping("/{id}")
    public String viewBill(
            @PathVariable Long id,
            Model model) {

        Billing billing = billingService.getBill(id);

        if (billing == null) {
            return "redirect:/billings";
        }

        model.addAttribute("billing", billing);

        return "billing/details";
    }

    @GetMapping("/pay/{id}")
    public String payBill(
            @PathVariable Long id) {

        billingService.markAsPaid(id);

        return "redirect:/billings";
    }

}