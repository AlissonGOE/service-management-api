package com.alisson.service_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientSummaryDTO {

    private Long id;
    private String name;
    private String phone;
}
