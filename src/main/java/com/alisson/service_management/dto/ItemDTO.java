package com.alisson.service_management.dto;

import com.alisson.service_management.enums.ServiceType;
import com.alisson.service_management.model.ServiceOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;
    private String name;
    private String description;
    private ServiceType serviceType;
    private Long serviceId;
}
