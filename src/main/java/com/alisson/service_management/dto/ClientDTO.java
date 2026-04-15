package com.alisson.service_management.dto;

import com.alisson.service_management.model.ServiceOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;
    private String phone;
    private List<ServiceOrder> services;
}
