package com.alisson.service_management.model;

import com.alisson.service_management.enums.ServiceStatus;
import com.alisson.service_management.enums.ServiceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    private ServiceStatus status;

    @Column(name = "service_type")
    private ServiceType serviceType;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "exit_date")
    private LocalDate exitDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<Item> items;

}
