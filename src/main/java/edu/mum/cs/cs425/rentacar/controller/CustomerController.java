package edu.mum.cs.cs425.rentacar.controller;

import edu.mum.cs.cs425.rentacar.entity.Customer;
import edu.mum.cs.cs425.rentacar.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String listCustomers(Model model) {

        model.addAttribute("customers",
                customerService.getAllCustomers());

        return "customer/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {

        model.addAttribute("customer",
                new Customer());

        return "customer/form";
    }

    @PostMapping("/save")
    public String saveCustomer(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult result) {

        if (result.hasErrors()) {
            return "customer/form";
        }

        customerService.saveCustomer(customer);

        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Customer customer =
                customerService.getCustomerById(id);

        if (customer == null) {
            return "redirect:/customers";
        }

        model.addAttribute("customer",
                customer);

        return "customer/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "redirect:/customers";
    }
}