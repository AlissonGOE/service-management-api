package com.alisson.service_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_service_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "status")
    private String status;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "exit_date")
    private LocalDate exitDate;
}
