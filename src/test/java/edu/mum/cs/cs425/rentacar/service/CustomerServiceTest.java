package edu.mum.cs.cs425.rentacar.service;

import edu.mum.cs.cs425.rentacar.entity.Customer;
import edu.mum.cs.cs425.rentacar.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldReturnAllCustomers() {

        when(customerRepository.findAll())
                .thenReturn(List.of(
                        Customer.builder().id(1L).firstName("John").build(),
                        Customer.builder().id(2L).firstName("Anna").build()
                ));

        List<Customer> customers = customerService.getAllCustomers();

        assertEquals(2, customers.size());
    }
}