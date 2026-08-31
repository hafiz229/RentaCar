package edu.mum.cs.cs425.rentacar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "billings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private Boolean paid;
}