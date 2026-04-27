package com.alisson.service_management.dto;

import com.alisson.service_management.enums.ServicePriority;
import com.alisson.service_management.enums.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrderDTO {

    private Long id;
    private String description;
    private BigDecimal totalValue;
    private ServiceStatus status;
    private ServicePriority servicePriority;
    private LocalDate entryDate;
    private LocalDate exitDate;

    private ClientSummaryDTO client;
    private List<ItemDTO> items;
}
